package andry.hais.appointment_api.api;

import andry.hais.appointment_api.models.Lesson;
import andry.hais.appointment_api.models.Prices;
import andry.hais.appointment_api.models.Schedule;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.service.StudentService;
import andry.hais.appointment_api.service.TeacherService;
import andry.hais.appointment_api.service.UserService;
import andry.hais.appointment_api.vo.request.PricesForm;
import andry.hais.appointment_api.vo.request.TimeIntervalForm;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@Api(value = "appointment api", description = "Operations pertaining to teacher in Appointment Api")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> getSchedules() {
        return ResponseEntity.ok(teacherService.getSchedules());
    }

    @GetMapping("/prices")
    public ResponseEntity<List<Prices>> getPrices() {
        return ResponseEntity.ok(teacherService.getPrices());
    }

    @PostMapping("/teacher/schedule")
    public boolean addSchedule(@RequestBody TimeIntervalForm timeIntervalForm, Principal principal) {
        User user = userService.findOne(principal.getName());
        try {
            teacherService.addSchedule(timeIntervalForm, user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PostMapping("/teacher/prices")
    public boolean addPrices(@RequestBody PricesForm pricesForm, Principal principal) {
        User user = userService.findOne(principal.getName());
        try {
            teacherService.addPrices(pricesForm, user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/teacher/approve/{lessonId}")
    public boolean approve(@PathVariable Long lessonId, Principal principal) {
        Lesson lesson = studentService.findOne(lessonId);
        User user = userService.findOne(principal.getName());
        try {
            teacherService.approveReservation(user, lesson);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/teacher/decline/{lessonId}")
    public boolean decline(@PathVariable Long lessonId, Principal principal) {
        Lesson lesson = studentService.findOne(lessonId);
        User user = userService.findOne(principal.getName());
        try {
            teacherService.declineReservation(user, lesson);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
