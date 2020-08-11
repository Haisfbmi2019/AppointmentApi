package andry.hais.appointment_api.api;

import andry.hais.appointment_api.models.Lesson;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.service.StudentService;
import andry.hais.appointment_api.service.UserService;
import andry.hais.appointment_api.vo.request.TimeIntervalForm;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
@Api(value = "appointment api", description = "Operations pertaining to student in Appointment Api")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @GetMapping("/lesson")
    public ResponseEntity<List<Lesson>> getLessons() {
        return ResponseEntity.ok(studentService.getLessons());
    }

    @PutMapping("/lesson/{teacherEmail}")
    public ResponseEntity<User> addLesson(@PathVariable String teacherEmail,
                                          @RequestBody TimeIntervalForm timeIntervalForm,
                                          Principal principal) {

        User student = userService.findOne(principal.getName());
        User teacher = userService.findOne(teacherEmail);

        studentService.reserveLesson(teacher, timeIntervalForm, student);
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping("/lesson/{id}")
    public ResponseEntity<Lesson> deleteReservation(@PathVariable Long id) {
        studentService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}
