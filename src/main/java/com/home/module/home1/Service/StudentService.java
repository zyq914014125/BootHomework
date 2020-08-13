package com.home.module.home1.Service;


import org.apache.commons.lang3.StringUtils;
import com.home.Serach.Result;
import com.home.Serach.Serachvo;
import com.home.module.home1.HiberMapper.StudentMapper;
import com.home.module.home1.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.X
 **/
@Repository
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    /**
     * 新增
     * @param student
     * @return
     */
    public Result<Student> InsertStudent(Student student){
      studentMapper .saveAndFlush(student);
      return new Result<Student>(Result.ResultState.SUCCESS_RESPONSE,"Insert SUCCESS",student);
    }

    /**
     * 查找某个学生
     * @param studentId
     * @return
     */
    public Student getOneStudentById(int  studentId){
        return studentMapper.findById(studentId).get();
    }

    /**
     * 由于无序，通过sort进行排序(根据studentName）
     * @return
     */
    public List<Student> getAllStudent(){
        return studentMapper.findAll(Sort.by(Sort.Direction.DESC,"studentName"));
    }

    /**
     * 分页
     * @param serachvo
     * @return
     */
    public Page<Student> getStudentBySerachvo(Serachvo serachvo){
        //orderby 判空，空按照默认id排序
        String orderby= StringUtils.isBlank(serachvo.getOrderBy())?"studentId":serachvo.getOrderBy();
        //sort 判空，排序规则空，默认降序,equalsIgnoreCase大小写忽略判等
        Sort.Direction direction=StringUtils.isBlank(serachvo.getSort())||serachvo.getSort().equalsIgnoreCase("DESC")?Sort.Direction.DESC:Sort.Direction.ASC;
        //Page 起始页 以orderby,direction为排序规则
        Pageable pageable= PageRequest.of(serachvo.getCurrentPage()-1,serachvo.getPageSize(),Sort.by(direction,orderby));
        //serachvo.getKeyword(),映射为student对象
        Student student=new Student();
        student.setStudentName(serachvo.getKeyWord());
        //ExampleMatcher 匹配器创建,并确立查询规则(根据name查询，并忽略ID)
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("studentName", ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("studentId");
        //创建例子，并加载匹配项与规则
        Example<Student> example=Example.of(student,matcher);
        return studentMapper.findAll(example,pageable);
    }


}
