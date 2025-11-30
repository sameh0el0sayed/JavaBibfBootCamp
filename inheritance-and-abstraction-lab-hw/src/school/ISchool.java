package school;

public interface ISchool {

    // Setters
    void setStudent(String studentName);
    void setHomework(String homeworkName);
    void setScore(int score);
    void setComplete(boolean isComplete);

    // Getters
    String getStudent();
    String getHomework();
    int getScore();
    boolean isComplete();
}

