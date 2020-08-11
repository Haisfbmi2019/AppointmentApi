package andry.hais.appointment_api.service;

import andry.hais.appointment_api.models.Lesson;
import andry.hais.appointment_api.models.Prices;
import andry.hais.appointment_api.models.Schedule;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.vo.request.PricesForm;
import andry.hais.appointment_api.vo.request.TimeIntervalForm;

import java.util.List;


public interface TeacherService {

    void addSchedule(TimeIntervalForm timeIntervalForm, User user);

    List<Schedule> getSchedules();

    List<Prices> getPrices();

    void addPrices(PricesForm pricesForm, User user);

    void approveReservation(User user, Lesson lesson);

    void declineReservation(User user, Lesson lesson);

}
