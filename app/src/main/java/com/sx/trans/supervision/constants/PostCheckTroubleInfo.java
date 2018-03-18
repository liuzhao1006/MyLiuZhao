package com.sx.trans.supervision.constants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mr_wang on 2017/9/4.
 * 查岗问题
 */

public class PostCheckTroubleInfo implements Serializable {


  private ArrayList<postcheckId> postcheckIds;
  private ArrayList<postcheck> postchecks;

    public class postcheckId{
        private int id=0;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class postcheck{
        private String answer;
        private String question;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }


}
