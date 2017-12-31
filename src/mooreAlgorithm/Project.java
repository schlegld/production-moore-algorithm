/*
 *   Copyright (c) David Schlegl 2017
 */
package mooreAlgorithm;

/**
 *
 * @author David Schlegl
 * 
 * Project class holds a single project and can compare projects
 * the class has to implement Comparable for Sorting
 * in this special case its just implemented for Project and not for the
 * more general case T
 */
public class Project implements Comparable<Project>{
    // fields for a single project
    private int projectName_pn;
    private int processTime_pj;
    private int dueDate_dj;
    private int completionTime_cj;
    private int tardiness_tj;

    // Constructor, initializes a singe project
    Project(int pn, int pj, int dj) {
        this.projectName_pn = pn;
        this.processTime_pj = pj;
        this.dueDate_dj = dj;
    }
    
    // Getter and Setter Methods for the Class
    // Write or return the specific values
    public int getProjectName_pn() {
        return projectName_pn;
    }

    public void setProjectName_pn(int projectName_pn) {
        this.projectName_pn = projectName_pn;
    }

    public int getProcessTime_pj() {
        return processTime_pj;
    }

    public void setProcessTime_pj(int processTime_pj) {
        this.processTime_pj = processTime_pj;
    }

    public int getDueDate_dj() {
        return dueDate_dj;
    }

    public void setDueDate_dj(int dueDate_dj) {
        this.dueDate_dj = dueDate_dj;
    }

    public int getCompletionTime_cj() {
        return completionTime_cj;
    }

    public void setCompletionTime_cj(int completionTime_cj) {
        this.completionTime_cj = completionTime_cj;
    }

    public int getTardiness_tj() {
        return tardiness_tj;
    }

    public void setTardiness_tj(int tardiness_tj) {
        this.tardiness_tj = tardiness_tj;
    }
    
    /** Override the compare method to tell the programm which fields you 
     * 
     * @param o 
     * @return a integer value depending if the dueDate of the actual Project is 
     * smaller or bigger than the dueDate of the Project o.
     */
    @Override
    public int compareTo(Project o) {
        return Integer.compare(dueDate_dj, o.dueDate_dj);
    }   
}
