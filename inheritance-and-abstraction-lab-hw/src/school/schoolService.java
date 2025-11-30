package school;

public class schoolService  implements ISchool {

        private String student;
        private String homework;
        private int score;
        private boolean complete;

        @Override
        public void setStudent(String studentName) {
            this.student = studentName;
        }

        @Override
        public void setHomework(String homeworkName) {
            this.homework = homeworkName;
        }

        @Override
        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public void setComplete(boolean isComplete) {
            this.complete = isComplete;
        }

        @Override
        public String getStudent() {
            return student;
        }

        @Override
        public String getHomework() {
            return homework;
        }

        @Override
        public int getScore() {
            return score;
        }

        @Override
        public boolean isComplete() {
            return complete;
        }
}
