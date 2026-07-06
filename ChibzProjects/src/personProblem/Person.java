package personProblem;

import java.util.HashMap;

public class Person {

    String name;
    HashMap<String, Problem> problems = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }

    public void addProblem(Problem problem) {
        problems.put(problem.name, problem);
    }

    public void solveProblem(String problemName) {
        Problem problem = problems.get(problemName);
        if (problem != null) {
            problem.solve();
        }
    }

    public void tellProblems() {
        System.out.println(name + "'s unsolved problems:");
        for (Problem problem : problems.values()) {
            if (!problem.isSolved) {
                System.out.println(problem.name + " - " + problem.type);
            }
        }
    }
}
