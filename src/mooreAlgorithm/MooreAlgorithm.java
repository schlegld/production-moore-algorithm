/*
 *   Copyright (c) David Schlegl 2017
 */
package mooreAlgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author David Schlegl
 *
 */
public class MooreAlgorithm {

    // The vector can hold only projects
    private Vector<Project> projectVector;
    private Configuration conf;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Starting point of the program
        // Create a new instance of the program (to call functions)
        MooreAlgorithm moore = new MooreAlgorithm();
        // init the projects
        moore.initProjects();
        // print the result
        moore.printProjects();
        // apply moore algorithm
        moore.mooreAlgorithm();
        // print the result
        System.out.println("\nResult:\n");
        moore.printProjects();
    }

    /**
     * Inits the Project
     */
    private void initProjects() {
        // creates an instance of the configuration class
        conf = new Configuration();
        // init the project vector, which holding the differnet projects
        projectVector = new Vector<Project>(conf.getJobsNameArray().length);
        // for loop to add all projects from the configuartion and add them 
        for (int i = 0; i < conf.getJobsNameArray().length; i++) {
            projectVector.add(new Project(conf.getJobsNameArray()[i], conf.getJobsProcessingTimeArray()[i], conf.getJobsDueDateArray()[i]));
        }
    }

    /**
     * implements the Morre Algorithm
     */
    private void mooreAlgorithm() {
        // termination criteria
        boolean stop = false;
        // sort the Vector
        sortEDD();
        // while no termination
        while (!stop) {
            stop = true;
            // calculate new comletion times and tardiness
            calculateValues();
            // print actual project list (the whole list of all projects which are
            // not removed
            printProjects();
            // check the whole project list
            for (int i = 0; i < projectVector.capacity(); i++) {
                // check if the tardiness is greater zero = the job is tardy
                if (projectVector.get(i).getTardiness_tj() > 0) {
                    // set termination criteria false => another loop is needed
                    stop = false;
                    // create a sublist which contains the elements of the vector
                    // from the first EDD sorted project till the project at index
                    // i where the first tardiness > 0
                    List<Project> subList = projectVector.subList(0, i + 1);
                    // sort the sublist (inline sorting with override method)
                    // overrides the compare and sortes the projects concerning 
                    // their processing times
                    Collections.sort(subList, new Comparator<Project>() {
                        @Override
                        public int compare(Project o1, Project o2) {
                            return Integer.compare(o1.getProcessTime_pj(), o2.getProcessTime_pj());
                        }
                    });
                    System.out.println("Lösche Element. Projekt " + subList.get(subList.size() - 1).getProjectName_pn() + " gelöscht.\n");
                    // gets the last project in the sublist (cause of the processing time sorting
                    // the last element has the hightest processing time
                    // remove this element from the vector
                    projectVector.removeElement(subList.get(subList.size() - 1));
                    // trim the size of the vector for the new loop (otherwise the index isn't changed
                    projectVector.trimToSize();
                    // terminates the for loop and starts the while loop
                    break;
                }
            }
        }
    }

    /**
     * Sorts the Vector
     */
    private void sortEDD() {
        // the Collection can sort the vector automatically 
        // override the compareTo Method for a special sorting
        // A special sorting is necassary cause we want to compare duedates (fields)
        Collections.sort(projectVector);
        System.out.println("\nSortierung nach EDD gestartet.\n");
    }

    /**
     * Print the projects
     */
    private void printProjects() {
        // init values
        int completionTime_cj = 0;
        int tardiness_tj = 0;
        int max_tardiness_tjMax = 0;
        // iterate over the whole vector, each element of the vector is returned
        // and converted to a project p
        for (Project p : projectVector) {
            // not necassary to set them in the single project but nice to have
            // calculates the completion time and the tardiness
            p.setCompletionTime_cj(completionTime_cj += p.getProcessTime_pj());
            p.setTardiness_tj(tardiness_tj = Integer.max(0, completionTime_cj - p.getDueDate_dj()));
            // prints the values at console
            System.out.println("Projekt: " + p.getProjectName_pn() + " hinzugefügt, Prozesszeit: "
                    + String.valueOf(p.getProcessTime_pj()) + " - Due Date: "
                    + String.valueOf(p.getDueDate_dj()) + " - Completion Time: "
                    + String.valueOf(completionTime_cj) + " - Tardiness: "
                    + String.valueOf(tardiness_tj));
            // checks if a new maximum tardiness value exists
            if (tardiness_tj > max_tardiness_tjMax) {
                max_tardiness_tjMax = tardiness_tj;
            }
        }
        // print maximum tardiness
        System.out.println("Maximale Verspätung: " + max_tardiness_tjMax);
    }

    /**
     * calculates the completion time and the tardiness
     */
    private void calculateValues() {
        int completionTime_cj = 0;
        for (Project p : projectVector) {
            // calculates the completion time and the tardiness
            p.setCompletionTime_cj(completionTime_cj += p.getProcessTime_pj());
            p.setTardiness_tj(Integer.max(0, completionTime_cj - p.getDueDate_dj()));
        }
    }
}
