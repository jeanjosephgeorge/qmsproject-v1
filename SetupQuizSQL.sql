DROP USER quiz CASCADE;

CREATE USER quiz
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT CONNECT TO quiz;
GRANT RESOURCE TO quiz;
GRANT CREATE SESSION TO quiz;
GRANT CREATE TABLE TO quiz;
GRANT CREATE VIEW TO quiz;

conn quiz/p4ssw0rd;

CREATE TABLE BATCH(
    batch_id number PRIMARY KEY,
    batch_name varchar2(50)
);

CREATE SEQUENCE USERS_ID_SEQ;
CREATE TABLE USERS(
    user_id number PRIMARY KEY,
    username varchar2(50) UNIQUE NOT NULL,
    password varchar2(50),
    first_name varchar2(100),
    last_name varchar2(100),
    user_role varchar2(15),
    batch_id number,
    CONSTRAINT batch_link FOREIGN KEY (batch_id) REFERENCES BATCH (batch_id)
);

CREATE SEQUENCE QUIZ_SEQ;
CREATE TABLE QUIZ(
    quiz_id number PRIMARY KEY,
    subject varchar2(50),
    description varchar2(200)
);

CREATE SEQUENCE QUESTION_SEQ;
CREATE TABLE QUESTIONS(
    question_id number PRIMARY KEY,
    question varchar2(300),
    ans1 varchar2(100),
    ans2 varchar2(100),
    ans3 varchar2(100),
    ans4 varchar2(100)
);

CREATE TABLE QZANDQS(
    quiz_id number,
    question_id number,
    CONSTRAINT qz_quiz_id FOREIGN KEY (quiz_id) REFERENCES QUIZ(quiz_id),
    CONSTRAINT qz_question_id FOREIGN KEY (question_id) REFERENCES QUESTIONS(question_id)
);


CREATE TABLE BATCH_ACCESS(
    batch_id number,
    quiz_id number,
    statuscomplete varchar2(10),
    CONSTRAINT batchid_batch_access FOREIGN KEY (batch_id) REFERENCES BATCH(batch_id),
    CONSTRAINT quizid_batch_access FOREIGN KEY (quiz_id) REFERENCES QUIZ(quiz_id)
);

CREATE TABLE RESULTS(
    user_id number,
    quiz_id number,
    grade number (7,2),
    statuscomplete varchar2(10),
    CONSTRAINT results_user_id FOREIGN KEY (user_id) REFERENCES USERS(user_id),
    CONSTRAINT results_quiz_id FOREIGN KEY (quiz_id) REFERENCES QUIZ(quiz_id)
);

-- CREATING BATCHES
INSERT INTO BATCH (batch_id, batch_name) VALUES (1, 'Java');
INSERT INTO BATCH (batch_id, batch_name) VALUES (2, 'Angular');
INSERT INTO BATCH (batch_id, batch_name) VALUES (3, 'Spring');
INSERT INTO BATCH (batch_id, batch_name) VALUES (4, 'Dynatrace');
INSERT INTO BATCH (batch_id, batch_name) VALUES (5, 'JMeter');

-- CREATING USERS
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'thejeanman','password','Jean','George','admin',1);
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'jaehwang','password','Jae','Hwang','user',3);
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'airton','password','Airton','Prado','user',2);
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'fermin','password','Fermin','Cuanelt','user',4);
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'takumi','password','Takumi','Shinohara','user',5);
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'boris','password','Boris','Sun','user',3);
INSERT INTO USERS (user_id, username, password, first_name, last_name, user_role, batch_id) VALUES (USERS_ID_SEQ.nextval, 'david','password','David','Quillen','user',1);

-- CREATING QUIZ
INSERT INTO QUIZ (quiz_id, subject, description) VALUES (1, 'Java', 'A quiz on Java fundamentals');
INSERT INTO QUIZ (quiz_id, subject, description) VALUES (2, 'Angular', 'Lets see what you know about Angular');
INSERT INTO QUIZ (quiz_id, subject, description) VALUES (3, 'JMeter', 'The definitive quiz on JMeter');

-- CREATING QUESTIONS
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Java?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Encapsulation?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Abstraction?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Inheritance?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Polymorphism?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is SQL?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Spring Framework?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is JMeter?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is JVisualVM?', 'Option 1','Option 2','Option 3','Option 4');
INSERT INTO QUESTIONS (question_id, question, ans1, ans2, ans3, ans4) VALUES (QUESTION_SEQ.nextval, 'What is Dynatrace?', 'Option 1','Option 2','Option 3','Option 4');

-- CREATING CONNECTIONS BETWEEN QUIZ AND QUESTIONS
INSERT INTO QZANDQS (quiz_id, question_id) VALUES (1,1);
INSERT INTO QZANDQS (quiz_id, question_id) VALUES (1,2);
INSERT INTO QZANDQS (quiz_id, question_id) VALUES (1,3);
INSERT INTO QZANDQS (quiz_id, question_id) VALUES (1,4);
INSERT INTO QZANDQS (quiz_id, question_id) VALUES (1,5);


commit;