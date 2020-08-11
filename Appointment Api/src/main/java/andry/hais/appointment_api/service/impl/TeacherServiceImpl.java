package andry.hais.appointment_api.service.impl;

import andry.hais.appointment_api.component.EmailSender;
import andry.hais.appointment_api.models.Lesson;
import andry.hais.appointment_api.models.Prices;
import andry.hais.appointment_api.models.Schedule;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.repository.LessonsRepository;
import andry.hais.appointment_api.repository.PricesRepository;
import andry.hais.appointment_api.repository.ScheduleRepository;
import andry.hais.appointment_api.repository.UserRepository;
import andry.hais.appointment_api.service.TeacherService;
import andry.hais.appointment_api.vo.request.PricesForm;
import andry.hais.appointment_api.vo.request.TimeIntervalForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PricesRepository pricesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LessonsRepository lessonsRepository;

    @Autowired
    EmailSender emailSender;

    @Override
    public void addSchedule(TimeIntervalForm timeIntervalForm, User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        Schedule savedSchedule = scheduleRepository.save(new Schedule(user.getProfile().getTeacher(),
                timeIntervalForm.getDateFrom(), timeIntervalForm.getDateTo()));

        oldUser.getProfile().getTeacher().getSchedules().add(savedSchedule);
        userRepository.save(oldUser);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Prices> getPrices() {
        return pricesRepository.findAll();
    }

    @Override
    public void addPrices(PricesForm pricesForm, User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        Prices savedPrices = pricesRepository.save(new Prices(user.getProfile().getTeacher(), pricesForm.getSubject(),
                pricesForm.getFifteenMinPrice(), pricesForm.getHalfAnHourPrice(), pricesForm.getHourPrice()));

        oldUser.getProfile().getTeacher().getPrices().add(savedPrices);
        userRepository.save(oldUser);
    }

    @Override
    public void approveReservation(User user, Lesson lesson) {
        Lesson oldLesson = lessonsRepository.findById(lesson.getId());
        oldLesson.setApproval(true);
        emailSender.send(user.getEmail(), "Вчитель підтвердив реєстрацію", "Вітаємо!");
        lessonsRepository.save(oldLesson);
    }

    @Override
    public void declineReservation(User user, Lesson lesson) {
        Lesson oldLesson = lessonsRepository.findById(lesson.getId());
        oldLesson.setApproval(false);
        emailSender.send(user.getEmail(), "На жальб вчитель скасував реєстрацію",
                "Будь ласка, спробуйте обрати інший час.");
        lessonsRepository.save(oldLesson);
    }

}
