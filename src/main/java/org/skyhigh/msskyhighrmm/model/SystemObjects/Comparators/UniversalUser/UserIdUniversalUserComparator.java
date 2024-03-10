package org.skyhigh.msskyhighrmm.model.SystemObjects.Comparators.UniversalUser;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;

import java.util.Comparator;

public class UserIdUniversalUserComparator implements Comparator<UniversalUser> {

    @Override
    public int compare(UniversalUser o1, UniversalUser o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
