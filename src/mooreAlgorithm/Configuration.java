/*
 *   Copyright (c) David Schlegl 2017
 */
package mooreAlgorithm;

/**
 *
 * @author David Schlegl
 * 
 * Configuaration class for the Project, sets the projects with 
 * processing time and due dates
 */
public class Configuration {
    private final int[] jobsNameArray = {1, 2, 3, 4, 5, 6, 7};
    private final int[] jobsProcessingTimeArray = {2, 4, 5, 6, 10, 12, 14};
    private final int[] jobsDueDateArray = {6, 12, 30, 19, 12, 18, 24};

    public int[] getJobsNameArray() {
        return jobsNameArray;
    }

    public int[] getJobsProcessingTimeArray() {
        return jobsProcessingTimeArray;
    }

    public int[] getJobsDueDateArray() {
        return jobsDueDateArray;
    }
}


