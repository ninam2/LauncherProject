package de.ninam.projects.launcher.execute.business;

import de.ninam.projects.launcher.client.ControlCommand;
import de.ninam.projects.launcher.client.LauncherClient;
import de.ninam.projects.launcher.execute.business.domain.*;
import de.ninam.projects.launcher.execute.clients.jenkins.utils.JenkinsCheckProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JenkinsCall {

    @Autowired
    private LauncherClient launcherClient;


    @Autowired
    private JenkinsCheckProvider jenkinsCheckProvider;


    public int testConnectionToJenkins() {
        try {
            List<Check> jenkinsChecks = jenkinsCheckProvider.provideChecks();
            return jenkinsChecks.size();

        } catch (Exception e) {
            // does this need further processing? fix it if it fails :D
            System.out.println("could not load job list from jenkins");
            throw new RuntimeException(e);
        }

    }

    public void instructions() {
        Page currentPage = jenkinsCheckProvider.jobstatus();
        Page lastPage = jenkinsCheckProvider.lastJobstatus();
        NumberPage numberPage = jenkinsCheckProvider.lastNumberJobstatus();
        NumberPage currentNumberPage = jenkinsCheckProvider.currentNumberJobstatus();
        Build lastSuccessfulBuild = currentPage.getLastSuccessfulBuild();
        Build lastFailedBuild = currentPage.getLastFailedBuild();

        List<CulPrits> culprits = numberPage.getCulprits();
        List<Build> builds = currentPage.getBuilds();
        int lastSuccessfulBuildNumber = lastSuccessfulBuild.getNumber();

        Collections.sort(builds);

        int currentBuildNumber = builds.get(0).getNumber();
        int lastBuildNumber = builds.get(1).getNumber();
        String lastNumberResult = numberPage.getResult();
        String currentNumberResult = currentNumberPage.getResult();
        String currentName = currentPage.getName();
        String currentColor = currentPage.getColor();

        String lastColor = lastPage.getColor();
        String culpritFullName = culprits.get(0).getFullName();


        System.out.println("Name:                      " + currentName);
        System.out.println("Color:                     " + currentColor);
        System.out.println("Old Color                  " + lastColor);
        System.out.println("Number:                    " + currentBuildNumber);
        System.out.println("Old Number:                " + lastBuildNumber);
        System.out.println("lastSuccessfulbuildNumber: " + lastSuccessfulBuildNumber);
        System.out.println("culpritFullName:           " + culpritFullName);
        System.out.println("currentresult:             " + currentNumberResult);
        System.out.println("last result:               " + lastNumberResult);

        if (!currentNumberResult.equals("SUCCESS")) {
            if (builds.size() > 1) {
                if (lastNumberResult.equals(currentNumberResult)) {
                    // nicht schießen, da vorher schon rot

                    launchTeam();
                    System.out.println("it fails again...");
                } else {
                    System.out.println("Number:     " + currentBuildNumber);
                    System.out.println("Old Number: " + lastBuildNumber);
                    System.out.println("new red build -> launch");
                    launchTeam();

                }
            } else {

                System.out.println("first red build -> launch");
            }
        } else {
            System.out.println("nicht schießen");
        }

    }

    boolean Team1() {
        NumberPage numberPage = jenkinsCheckProvider.lastNumberJobstatus();
        List<CulPrits> culprits = numberPage.getCulprits();
        String culpritFullName = culprits.get(0).getFullName();
        if (culpritFullName.equals("nina-marie.mueller") || culpritFullName.equals("conny.strecker") || culpritFullName.equals("tobias.knop") || culpritFullName.equals("tknop")
                || culpritFullName.equals("nmueller") || culpritFullName.equals("cstrecker") || culpritFullName.equals("swaschnick")
                || culpritFullName.equals("aglintschert") || culpritFullName.equals("alexander.glintschert")) {
            return true;
        } else {
            return false;
        }
    }

    boolean Team2() {
        NumberPage numberPage = jenkinsCheckProvider.lastNumberJobstatus();
        List<CulPrits> culprits = numberPage.getCulprits();
        String culpritFullName = culprits.get(0).getFullName();
        if (culpritFullName.equals("twendzinski") || culpritFullName.equals("twendzin") || culpritFullName.equals("thomas.wendzinski") || culpritFullName.equals("swaschnick")
                || culpritFullName.equals("swaschni") || culpritFullName.equals("sebastian.waschnick") || culpritFullName.equals("swaschnick")
                || culpritFullName.equals("twendzin") || culpritFullName.equals("thomas.wendzinski") || culpritFullName.equals("kkessler")
                || culpritFullName.equals("katja.kessler11") || culpritFullName.equals("georgy.dobrev") || culpritFullName.equals("gdobrev")
                || culpritFullName.equals("dawerkow") || culpritFullName.equals("thomas.wendzinski") || culpritFullName.equals("mbluemcke")) {
            return true;
        } else {
            return false;
        }
    }

    public void launchTeam() {
        if (Team1()) {
            for (int i = 1; i <= 24; i++) {
                launcherClient.control(ControlCommand.RIGHT);
            }
            launcherClient.control(ControlCommand.UP, ControlCommand.LAUNCH, ControlCommand.ZERO);
        } else {
            if (Team2()) {
                for (int i = 1; i <= 12; i++) {
                    launcherClient.control(ControlCommand.RIGHT);
                }
                launcherClient.control(ControlCommand.UP, ControlCommand.LAUNCH, ControlCommand.ZERO);
            } else {
                System.out.println("Außer Reichweite");
            }
        }
    }
}





