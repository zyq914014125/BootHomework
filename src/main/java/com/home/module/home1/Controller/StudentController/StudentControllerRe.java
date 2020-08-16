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
@RequestMapping("/student")
public class StudentControllerRe {
    @Autowired
    StudentService studentService;

    @PostMapping ("/insertStrudent")
    public Result<Student> InsertStudent(@RequestBody Student student){
        return studentService.InsertStudent(student);
    }
    @PostMapping("/studentPage")
    public Page<Student> StudentPage(@RequestBody Serachvo serachvo){
        return studentService.getStudentBySerachvo(serachvo);
    }
    @GetMapping("/student/{studentId}")
    public Student FindOneStudent(@PathVariable int studentId){
        return studentService.getOneStudentById(studentId);
    }
    @GetMapping("/studentALL")
    public List<Student> FindAll(){
        return studentService.getAllStudent();
    }
}
