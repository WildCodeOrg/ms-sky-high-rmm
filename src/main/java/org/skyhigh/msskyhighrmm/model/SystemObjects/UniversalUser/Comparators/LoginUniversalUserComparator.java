package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Comparators;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;

import java.util.Comparator;

public class LoginUniversalUserComparator implements Comparator<UniversalUser> {
    @Override
    public int compare(UniversalUser o1, UniversalUser o2) {
        return o1.getLogin().compareTo(o2.getLogin());
    }
}
