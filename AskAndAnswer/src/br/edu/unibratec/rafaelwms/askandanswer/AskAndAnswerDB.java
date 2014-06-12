package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AskAndAnswerDB {

	private AskAndAnswerHelperDB helper;

	public AskAndAnswerDB(Context ctx) {
		helper = new AskAndAnswerHelperDB(ctx);
	}

	private ContentValues userValues(User user) {

		ContentValues valuesUser = new ContentValues();
		
		valuesUser.put("login", user.getLogin_user());
		valuesUser.put("password", user.getPassword_user());
		if (!user.isMaster_user()) {
			valuesUser.put("master", 0);
		} else if (user.isMaster_user()){
			valuesUser.put("master", 1);
		}
		return valuesUser;
	}
	
	
	private ContentValues testValues(Test test) {

		ContentValues values = new ContentValues();

		values.put("user", test.getUser().getId_user());
		values.put("title", test.getText_title());
		values.put("category", test.getCategory());
		values.put("value_test", test.getTest_value());

		return values;
	}
	
	private ContentValues questionValues(Question quest) {

		ContentValues values = new ContentValues();

		values.put("test", quest.getTest());
		values.put("number", quest.getNumber());
		values.put("txt_question", quest.getText());

		return values;
	}
	
	private ContentValues answerValues(Answer answer) {

		ContentValues values = new ContentValues();

		values.put("question", answer.getQuestion());
		values.put("number", answer.getNumber());
		values.put("txt_answer", answer.getText());
		if(!answer.isCorrect()){
			values.put("correct", 0);
		}else if(answer.isCorrect()){
			values.put("correct", 1);
		}
		values.put("answer_value", answer.getValue());
		return values;
	}
	
	private ContentValues answeredQuestionValues(AnsweredQuestion aq) {

		ContentValues values = new ContentValues();

		values.put("user", aq.getUser().getId_user());
		values.put("question", aq.getQuestion().getId_question());
		if(!aq.isCheckedAnswer1()){
			values.put("answer1", 0);
		} else if(aq.isCheckedAnswer1()){
			values.put("answer1", 1);
		}
		if(!aq.isCheckedAnswer2()){
			values.put("answer2", 0);
		} else if(aq.isCheckedAnswer2()){
			values.put("answer2", 1);
		}
		if(!aq.isCheckedAnswer3()){
			values.put("answer3", 0);
		} else if(aq.isCheckedAnswer3()){
			values.put("answer3", 1);
		}
		if(!aq.isCheckedAnswer4()){
			values.put("answer4", 0);
		} else if(aq.isCheckedAnswer4()){
			values.put("answer4", 1);
		}
		if(!aq.isCheckedAnswer5()){
			values.put("answer5", 0);
		} else if(aq.isCheckedAnswer1()){
			values.put("answer5", 1);
		}
		values.put("amount", aq.getAmount());
		
		return values;
	}

	public void insertUser(User user) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = userValues(user);
		db.insert("user", null, values);
		db.close();
	}
	
	public void insertTest(Test test) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = testValues(test);
		db.insert("test", null, values);
		db.close();
	}
	
	public void insertQuestion(Question quest) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = questionValues(quest);
		db.insert("question", null, values);
		db.close();
	}

	public void insertAnswer(Answer answer) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = answerValues(answer);
		db.insert("answer", null, values);
		db.close();
	}
	
	
	public void insertAnsweredQuestion(AnsweredQuestion aq) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = answeredQuestionValues(aq);
		db.insert("aquestion", null, values);
		db.close();
	}
	
	public void alterUser(User user) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = userValues(user);
		db.update("user", values, "_id = ?", new String[] { user.getId_user()+"" });
		// +"" = Estagiário com raiva... rsrsrsrs
		db.close();
	}
	
	public void alterTest(Test test) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = testValues(test);
		db.update("test", values, "_id = ?", new String[] { test.getId_test()
				+ "" });
		// +"" = Estagiário com raiva... rsrsrsrs
		db.close();
	}
	
	public void alterQuestion(Question quest) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = questionValues(quest);
		db.update("question", values, "_id = ?", new String[] { quest.getId_question()
				+ "" });
		db.close();
	}
	
	public void alterAnswer(Answer answer) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = answerValues(answer);
		db.update("answer", values, "_id = ?", new String[] { answer.getId_answer()
				+ "" });
		db.close();
	}
	
	public void alterAnsweredQuestion(AnsweredQuestion aq) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = answeredQuestionValues(aq);
		db.update("answer", values, "user = ? and question = ?", new String[] { aq.getUser().getId_user()
				+ "", aq.getQuestion().getId_question()+"" });
		db.close();
	}
	
	public void deleteUser(User user) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("user", "_id = ?", new String[] { user.getId_user() + "" });
		db.close();
	}

	public void deleteTest(Test test) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("test", "_id = ?", new String[] { test.getId_test() + "" });
		db.close();
	}
	
	public void deleteQuestion(Question quest) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("question", "_id = ?", new String[] { quest.getId_question() + "" });
		db.close();
	}
	
	public void deleteAnswer(Answer answer) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("answer", "_id = ?", new String[] { answer.getId_answer() + "" });
		db.close();
	}
	
	public void deleteAnsweredQuestion(AnsweredQuestion aq) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("answer", "user = ? and question = ?", new String[] { aq.getUser().getId_user() + "", aq.getQuestion().getId_question()+"" });
		db.close();
	}
	
	private User fillUser(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String login = cursor.getString(cursor.getColumnIndex("login"));
		String password = cursor.getString(cursor.getColumnIndex("password"));
		boolean master = false;
		if (cursor.getInt(cursor.getColumnIndex("master")) == 1) {
			master = true;
		}

		User user = new User(id, login, password, master);
		return user;
	}
	
	private Answer fillAnswer(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int quest_id = cursor.getInt(cursor.getColumnIndex("question"));
		int number = cursor.getInt(cursor.getColumnIndex("number"));
		String txt_answer = cursor.getString(cursor.getColumnIndex("txt_answer"));
		double answer_value = cursor.getDouble(cursor.getColumnIndex("answer_value"));	
		boolean correct = false;
		if(cursor.getInt(cursor.getColumnIndex("correct")) == 1){
			correct = true;
		}		

		Answer answer = new Answer(id, quest_id, number, txt_answer, correct, answer_value);
		return answer;
	}
	

	private Test fillTest(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int user_id = cursor.getInt(cursor.getColumnIndex("user"));
		String title = cursor.getString(cursor.getColumnIndex("title"));
		String category = cursor.getString(cursor.getColumnIndex("category"));
		double value_test = cursor.getDouble(cursor.getColumnIndex("value_test"));
		
		User user = new User();
		user = findUserById(user_id);
		
		Test test = new Test(id, user, title, category, null, value_test);
		return test;
	}
	
	private Question fillQuestion(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int test_id = cursor.getInt(cursor.getColumnIndex("test"));
		int number = cursor.getInt(cursor.getColumnIndex("number"));
		String txt_question = cursor.getString(cursor.getColumnIndex("txt_question"));
		
		Test test = findTestById(test_id);
	
		Question quest = new Question(id, test, number, txt_question, null);
		return quest;
	}
	
	private Question fillQuestionByTest(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int test_id = cursor.getInt(cursor.getColumnIndex("test"));
		int number = cursor.getInt(cursor.getColumnIndex("number"));
		String txt_question = cursor.getString(cursor.getColumnIndex("txt_question"));
		
		Test test = findTestById(test_id);
		List<Answer> answers = findAnswersByQuestionId(id);
	
		Question quest = new Question(id, test, number, txt_question, answers);
		return quest;
	}
	
	private Question fillQuestionById(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int test_id = cursor.getInt(cursor.getColumnIndex("test"));
		int number = cursor.getInt(cursor.getColumnIndex("number"));
		String txt_question = cursor.getString(cursor.getColumnIndex("txt_question"));
		
		Test test = findTestById(test_id);
	
		Question quest = new Question(id, test, number, txt_question, null);
		return quest;
	}
	
	private Test fillUserTest(Cursor cursor, User user) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String title = cursor.getString(cursor.getColumnIndex("title"));
		String category = cursor.getString(cursor.getColumnIndex("category"));
		double value_test = cursor.getDouble(cursor.getColumnIndex("value_test"));
	

		Test test = new Test(id, user, title, category, null, value_test);
		return test;
	}
	
	private AnsweredQuestion fillAnsweredQuestion(Cursor cursor){
		
		User user = findUserById(cursor.getInt(cursor.getColumnIndex("user")));
		Question question = findQuestionsById(cursor.getInt(cursor.getColumnIndex("question")));
		boolean ans1 = false;
		if(cursor.getInt(cursor.getColumnIndex("answer1")) == 1){
			ans1 = true;
		}
		boolean ans2 = false;
		if(cursor.getInt(cursor.getColumnIndex("answer2")) == 1){
			ans2 = true;
		}
		boolean ans3 = false;
		if(cursor.getInt(cursor.getColumnIndex("answer3")) == 1){
			ans3 = true;
		}
		boolean ans4 = false;
		if(cursor.getInt(cursor.getColumnIndex("answer4")) == 1){
			ans4 = true;
		}
		boolean ans5 = false;
		if(cursor.getInt(cursor.getColumnIndex("answer5")) == 1){
			ans5 = true;
		}
		double amount = cursor.getDouble(cursor.getColumnIndex("amount"));
		
		AnsweredQuestion aq = new AnsweredQuestion(user, question, ans1, ans2, ans3, ans4, ans5, amount);
		
		return aq;
	}
	

	public List<Test> allTests(){
		List<Test> tests = new ArrayList<Test>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from test", null);
		
		while (cursor.moveToNext()){
			Test test = fillTest(cursor);
			test.setQuestions(findQuestionsByTest(test));
			tests.add(test);
		}
		cursor.close();
		db.close();
		return tests;
	}
	
	public List<Test> findTestByUser(User user){
		List<Test> tests = new ArrayList<Test>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from test where user = ?", new String[]{user.getId_user()+""});
		
		while (cursor.moveToNext()){
			Test test = fillUserTest(cursor, user);
			test.setQuestions(findQuestionsByTest(test));
			tests.add(test);
		}
		cursor.close();
		db.close();
		return tests;
	}
	
	public List<Question> findQuestionsByTest(Test test){
		List<Question> questions = new ArrayList<Question>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from question where test = ?", new String[]{test.getId_test()+""});
		
		while (cursor.moveToNext()){
			Question quest = fillQuestionByTest(cursor);
			questions.add(quest);
		}
		cursor.close();
		db.close();
		return questions;
	}
	
	public List<Question> findQuestionsByTestId(int id){
		List<Question> questions = new ArrayList<Question>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from question where test = ?", new String[]{id+""});
		
		while (cursor.moveToNext()){
			Question quest = fillQuestionByTest(cursor);
			questions.add(quest);
		}
		cursor.close();
		db.close();
		return questions;
	}
	
	public Question findQuestionByTestAndNumber(Test test, int number){
	Question question = new Question();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from question where test = ? and number = ?", new String[]{test.getId_test()+"", number+""});
		
		while (cursor.moveToNext()){
			question = fillQuestion(cursor);
		}
		cursor.close();
		db.close();
		return question;
	}
	
	public List<Answer> findAnswersByQuestion(Question quest){
		List<Answer> answers = new ArrayList<Answer>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from answer where question = ?", new String[]{quest.getId_question()+""});
		
		while (cursor.moveToNext()){
			Answer answer = fillAnswer(cursor);
			answers.add(answer);
		}
		cursor.close();
		db.close();
		return answers;
	}
	
	public List<Answer> findAnswersByQuestionId(int id){
		List<Answer> answers = new ArrayList<Answer>();
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from answer where question = ?", new String[]{id+""});
		
		while (cursor.moveToNext()){
			Answer answer = fillAnswer(cursor);
			answers.add(answer);
		}
		cursor.close();
		db.close();
		return answers;
	}
	
	public List<Answer> findAnswersByTest(Test test){
		List<Answer> answers = new ArrayList<Answer>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select ans._id, ans.question from answer as ans inner join question as qst on ans.question = qst._id where qst.test = ?", new String[]{test.getId_test()+""});
		
		while (cursor.moveToNext()){
			Answer answer = new Answer();
			answer = findAnswerById(cursor.getInt(0));	
			answers.add(answer);
		}
		cursor.close();
		db.close();
		return answers;
	}
	
	
	public  User findUserById(int id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		User user = new User();
		Cursor cursor = db.rawQuery(
				"select * from user where _id = ? ",
				new String[] { id+""});

		if (cursor.moveToFirst() && cursor.getCount() >= 1) {
			
			user = fillUser(cursor);
			cursor.close();
			db.close();
			return user;
			
		} else {
			cursor.close();
			db.close();
			return null;
		}
	}
	
		
		public Test findTestById(int id){
			Test tst = new Test();
			
			SQLiteDatabase db = helper.getReadableDatabase();
			
			Cursor cursor = db.rawQuery(
					"select * from test where _id = ?", new String[]{id+""});
			
			if (cursor.moveToFirst() && cursor.getCount() >= 1){
				 tst= fillTest(cursor);	
				 tst.setUser(findUserById(tst.getUser().getId_user()));
			}
			cursor.close();
			db.close();
			return tst;		
	}
	
		public Question findQuestionsById(int id){
			Question question = new Question();
			List<Answer> answers = findAnswersByQuestionId(id);
			SQLiteDatabase db = helper.getReadableDatabase();
			
			Cursor cursor = db.rawQuery(
					"select * from question where _id = ?", new String[]{id+""});
			
			while (cursor.moveToFirst() && cursor.getCount() >= 1){		
				question = fillQuestionById(cursor);
				question.setAnswers(answers);
			}
			cursor.close();
			db.close();
			return question;
		}

		
		public Answer findAnswerById(int id){
			Answer answer = new Answer();
			
			SQLiteDatabase db = helper.getReadableDatabase();
			
			Cursor cursor = db.rawQuery(
					"select * from answer where _id = ?", new String[]{id+""});
			
			while (cursor.moveToFirst() && cursor.getCount() >= 1){
				answer = fillAnswer(cursor);
			}
			cursor.close();
			db.close();
			return answer;
		}

		public User login(String login, String passwd) {
			SQLiteDatabase db = helper.getReadableDatabase();
			User user = new User();
			Cursor cursor = db.rawQuery(
					"select * from user where login = ? and password = ?",
					new String[] { login, passwd });

			if (cursor.moveToFirst() && cursor.getCount() >= 1) {
				
				user = fillUser(cursor);
				cursor.close();
				db.close();
				return user;
				
			} else {
				cursor.close();
				db.close();
				return null;
			}
		}
		
		
		public List<AnsweredQuestion> findAnsweredQuestionsByUser(User user) {
			List<AnsweredQuestion> aqs = new ArrayList<>();
			SQLiteDatabase db = helper.getReadableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from aquestion where user = ?",
					new String[] { user.getId_user()+"" });

			while (cursor.moveToNext()) {
				AnsweredQuestion aq = fillAnsweredQuestion(cursor);
				aqs.add(aq);
			}
				cursor.close();
				db.close();
				return aqs;

		}

		
}
