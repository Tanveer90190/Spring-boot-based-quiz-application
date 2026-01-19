package com.example.Quizapp.service;

import com.example.Quizapp.model.Question;
import com.example.Quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestion(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
        }
         return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.GONE);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(int id,Question updatedQuestion) {
         Optional<Question> optionalQuestion=questionDao.findById(id);
         if (optionalQuestion.isPresent()){
             Question existingQuestion = optionalQuestion.get();
             existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
             existingQuestion.setOption1(updatedQuestion.getOption1());
             existingQuestion.setOption2(updatedQuestion.getOption2());
             existingQuestion.setOption3(updatedQuestion.getOption3());
             existingQuestion.setOption4(updatedQuestion.getOption4());
             existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
             existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
             existingQuestion.setCategory(updatedQuestion.getCategory());
             questionDao.save(existingQuestion);
             return new ResponseEntity<>("Updated",HttpStatus.OK);
         }
         return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }
}
