CREATE TABLE quizapp.question (
    org uuid,
    exam uuid,
    subject uuid,
    year int,
    id uuid,
    AnsDescription text,
    answertype frozen<answertype>,
    correct_answer text,
    option_four text,
    option_one text,
    option_three text,
    option_two text,
    question text,
    PRIMARY KEY ((org, exam, subject), year, id)
);
CREATE TABLE quizapp.questionbyid (
    org uuid,
    exam uuid,
    subject uuid,
    year int,
    id uuid,
    AnsDescription text,
    answertype frozen<answertype>,
    correct_answer text,
    option_four text,
    option_one text,
    option_three text,
    option_two text,
    question text,
    PRIMARY KEY ((id), year,org, exam, subject )
);
CREATE TABLE quizapp.paper (
    paperid uuid,
    org uuid,
    exam uuid,
    startdate text,
    starttime date,
    durationofexam duration,
    subjects list<FROZEN<subjects>>,
    ispublic boolean,    
    marksforeachquestion int,
    numberofquestions int,
    questions list<FROZEN<questions>>,
    description text,
    isminusmarking boolean,
    minusmarkingby decimal,
    desclaimerforexam list<FROZEN<disclaimers>>,
    isonebyone boolean,
    isinbluksubmit boolean,
    PRIMARY KEY ((org,exam,startdate), paperid)
);
CREATE TYPE IF NOT EXISTS quizapp.disclaimers (
    desclaimer string
);

CREATE TYPE IF NOT EXISTS quizapp.subjects (
    subject uuid
);
CREATE TYPE IF NOT EXISTS quizapp.questions (
    subject uuid
);

CREATE TABLE mcqstudents.student (
    name varchar(50)
    phonenumber varchar(10)
    emailid text
    create_ts date,
    createdby uuid,
    exam uuid
    PRIMARY KEY ((name,phonenumber), exam)
);
CREATE TABLE mcqstudents.student (
    name varchar(50)
    phonenumber varchar(10)
    emailid text
    create_ts date,
    createdby uuid,
    exam uuid    
    PRIMARY KEY ((name,phonenumber), exam)
);
CREATE TABLE mcqstudents.studentcredit (
    name varchar(50),
    phonenumber varchar(10),
    creditvalue decimal
    recentlyaddcredit decimal
    PRIMARY KEY (name,phonenumber)
);
CREATE TABLE mcqstudents.examstaken (
    name varchar(50)
    phonenumber varchar(10)
    paper uuid,
    markobtained int,
    totalmarks int,
    status varchar(50)
);
CREATE TABLE mcqstudents.examattamptdetails (
    name varchar(50)
    phonenumber varchar(10)
    paper uuid,
    markobtained int,
    totalmarks int,
    questionattampted list<FROZEN<questionattampted>>
);
CREATE TYPE IF NOT EXISTS quizapp.questionattampted (
    question uuid,
    answeredoption string
);


CREATE TABLE quizapp.paper (
    org uuid,
    exam uuid,
    startdate text,
    paperid uuid,
    desclaimerforexam list<frozen<disclaimers>>,
    description text,
    durationofexam int,
    isinbluksubmit boolean,
    isminusmarking boolean,
    isonebyone boolean,
    ispublic boolean,
    marksforeachquestion int,
    minusmarkingby decimal,
    numberofquestions int,
    questions list<frozen<questions>>,
    starttime text,
    subjects list<frozen<subjects>>,
    orgname text,
    examname text,
    PRIMARY KEY ((org, exam),startdate, paperid)
)