package personProblem;

public class Problem {

    String name;
    ProblemType type;
    boolean isSolved = false;

    public Problem(String name, ProblemType type) {
        this.name = name;
        this.type = type;
    }

    public void solve() {
        isSolved = true;
    }
}
