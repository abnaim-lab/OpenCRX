package modelobjects;

import java.util.ArrayList;
import java.util.List;

public class RegistrationResult {


    private boolean registerResult = true;
    private List<String> problemsNotification = new ArrayList<String>();


    public RegistrationResult() {

    }


    public boolean getRegiResult() {
        return registerResult;
    }

    public void setRegiResult(boolean result) {
        this.registerResult = result;
    }

    public void setproblemsNotification(List<String> list) {
        this.problemsNotification = list;
    }

    public List<String> getProblemsNotification() {

        return problemsNotification;
    }
}
