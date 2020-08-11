package andry.hais.appointment_api.service.impl;

import andry.hais.appointment_api.component.EmailSender;
import andry.hais.appointment_api.enums.ResultEnum;
import andry.hais.appointment_api.exception.MyException;
import andry.hais.appointment_api.models.Lesson;
import andry.hais.appointment_api.models.Schedule;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.repository.LessonsRepository;
import andry.hais.appointment_api.repository.ScheduleRepository;
import andry.hais.appointment_api.repository.UserRepository;
import andry.hais.appointment_api.service.StudentService;
import andry.hais.appointment_api.vo.request.TimeIntervalForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    ScheduleRepository studentRepository;

    @Autowired
    LessonsRepository lessonsRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSender emailSender;

    @Override
    public Lesson findOne(Long id) {
        return lessonsRepository.findById(id);
    }

    @Override
    public void sendMessages(User teacherUser, Lesson savedLesson, TimeIntervalForm timeIntervalForm, User studentUser) {

        String message = "Нове заняття, з учнем - " + studentUser.getEmail() + ", у "
                + timeIntervalForm.getDateFrom().getDayOfWeek() + " з - " + timeIntervalForm.getDateFrom().getHour() +
                timeIntervalForm.getDateFrom().getMinute() + " по - "
                + timeIntervalForm.getDateTo().getHour() + timeIntervalForm.getDateTo().getMinute() +
                " посилання для схвалення - http://localhost:8000/teacher/approve/{"
                + savedLesson.getId() + "} , для відміни - http://localhost:8000/teacher/decline/{" +
                +savedLesson.getId() + "} ";
        emailSender.send(teacherUser.getEmail(), "Твій университет", message);

        String message2 = "Вітаємо з успішною резервацією заняття, зачекайте будьласка на рішення викладача." +
                " Для відміни заняттия, скористайтеся посиланням - " + "http://localhost:8000/student/lesson/{" +
                savedLesson.getId() + "} ";
        emailSender.send(studentUser.getEmail(), "Твій университет", message2);

    }

    @Override
    public void reserveLesson(User teacherUser, TimeIntervalForm timeIntervalForm, User studentUser) {
        User oldStudent = userRepository.findByEmail(studentUser.getEmail());

        List<Schedule> schedules = scheduleRepository.findAllByDateFromBeforeAndDateToAfter(timeIntervalForm.getDateFrom(),
                timeIntervalForm.getDateTo());
        List<Lesson> count = lessonsRepository.countReservedLessons(timeIntervalForm.getDateFrom(), timeIntervalForm.getDateTo());

        if (!schedules.isEmpty()) {
            if (count.isEmpty()) {

                Lesson savedLesson = lessonsRepository.save(new Lesson(teacherUser.getProfile().getTeacher(), studentUser.getProfile().getStudent(),
                        timeIntervalForm.getDateFrom(), timeIntervalForm.getDateTo()));
                oldStudent.getProfile().getStudent().getLessons().add(savedLesson);

                sendMessages(teacherUser, savedLesson, timeIntervalForm, studentUser);

                userRepository.save(oldStudent);
            } else {
                throw new MyException(ResultEnum.TIME_IS_ALREADY_RESERVED);
            }
        } else {
            throw new MyException(ResultEnum.TEACHER_HAS_NO_TIME);
        }

    }

    @Override
    public List<Lesson> getLessons() {
        return lessonsRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {
        lessonsRepository.delete(findOne(id));
    }

}
