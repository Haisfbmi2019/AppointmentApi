package andry.hais.appointment_api.service;

import andry.hais.appointment_api.models.Lesson;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.vo.request.TimeIntervalForm;

import java.util.List;

public interface StudentService {

    Lesson findOne(Long id);

    void sendMessages(User teacherUser, Lesson savedLesson, TimeIntervalForm timeIntervalForm, User studentUser);

    void reserveLesson(User teacher, TimeIntervalForm timeIntervalForm, User student);

    List<Lesson> getLessons();

    void deleteReservation(Long id);
}
