package com.home.module.home1.Controller.StudentController;

import com.home.Serach.Result;
import com.home.Serach.Serachvo;
import com.home.module.home1.Service.StudentService;
import com.home.module.home1.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr.X
 **/
@RestController
@RequestMapping("/Student")
public class StudentControllerRe {
    @Autowired
    StudentService studentService;

    @PostMapping ("/InsertStrudent")
    public Result<Student> InsertStudent(@RequestBody Student student){
        return studentService.InsertStudent(student);
    }
    @PostMapping("/StudentPage")
    public Page<Student> StudentPage(@RequestBody Serachvo serachvo){
        return studentService.getStudentBySerachvo(serachvo);
    }
    @GetMapping("/Student/{studentId}")
    public Student FindOneStudent(@PathVariable int studentId){
        return studentService.getOneStudentById(studentId);
    }
    @GetMapping("/StudentALL")
    public List<Student> FindAll(){
        return studentService.getAllStudent();
    }
}
