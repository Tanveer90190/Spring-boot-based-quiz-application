package com.example.Quizapp.service;

import com.example.Quizapp.dao.QuestionDao;
import com.example.Quizapp.dao.QuizDao;
import com.example.Quizapp.model.Question;
import com.example.Quizapp.model.QuestionWrapper;
import com.example.Quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions=questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
      Optional<Quiz> quiz=quizDao.findById(id);
      List<Question> questionsFromDB=quiz.get().getQuestions();
      List<QuestionWrapper> questionsForUser=new ArrayList<>();
      for(Question q:questionsFromDB){
          QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
          questionsForUser.add(qw);
      }
      return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }
}
