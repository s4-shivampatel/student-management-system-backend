package com.shivmkp.student_management_system.controller;




import com.shivmkp.student_management_system.dto.CreateStudentDto;
import com.shivmkp.student_management_system.dto.FilterStudentDto;
import com.shivmkp.student_management_system.dto.UpdateStudentDto;
import com.shivmkp.student_management_system.entity.Student;
import com.shivmkp.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> createStudents(@Valid @RequestBody CreateStudentDto createStudentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(createStudentDto));
    }

                        /*Getting All Students */
//    @GetMapping
//    public ResponseEntity<List<Student>> getAllStudents(){
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAllStudent());
//    }
                        /*Getting Students by Paging and Sorting */
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                        @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                        @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
                                                        FilterStudentDto filterStudentDto){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAllStudent(pageNumber,pageSize,sortBy,sortDir, filterStudentDto));
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getAllStudentsById(@PathVariable Long studentId ){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAllStudentById(studentId));
    }


    @PutMapping("/{studentId}")
    public ResponseEntity<Student> putUpdateStudent(@Valid @PathVariable Long studentId, @RequestBody UpdateStudentDto updateStudentDto){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.putUpdateStudent(studentId,updateStudentDto));
    }
    @PatchMapping("/{studentId}")
    public ResponseEntity<Student> patchUpdateStudent(@Valid @PathVariable Long studentId, @RequestBody UpdateStudentDto updateStudentDto){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.patchUpdateStudent(studentId,updateStudentDto));
    }


    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
         studentService.deleteStudentById(studentId);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
