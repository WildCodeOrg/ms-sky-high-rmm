package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator.ValidatingEnums.SortDirection;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Comparators.LoginUniversalUserComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Comparators.UserIdUniversalUserComparator;
import org.skyhigh.msskyhighrmm.validation.annotations.NotEmpty;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class UniversalUserSort {

    @NotEmpty
    private String direction;

    @NotEmpty
    private String sortBy;



    public static void sort(ArrayList<UniversalUser> usersListToSort, UniversalUserSort universalUserSort)
    {
        if (usersListToSort == null || usersListToSort.isEmpty())
            return;

        SortDirection sortDirection = SortDirection.valueOf(universalUserSort.direction);
        UniversalUserSortParameter sortParameter = UniversalUserSortParameter.valueOf(universalUserSort.sortBy);

        switch (sortDirection) {
            case ASC -> {
                if (sortParameter == UniversalUserSortParameter.LOGIN) {
                    usersListToSort.sort(new LoginUniversalUserComparator());
                } else if (sortParameter == UniversalUserSortParameter.USER_ID) {
                    usersListToSort.sort(new UserIdUniversalUserComparator());
                }
            }
            case DESC -> {
                if (sortParameter == UniversalUserSortParameter.LOGIN) {
                    usersListToSort.sort(new LoginUniversalUserComparator().reversed());
                } else if (sortParameter == UniversalUserSortParameter.USER_ID) {
                    usersListToSort.sort(new UserIdUniversalUserComparator().reversed());
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || (
                (direction == null ? ((UniversalUserSort) obj).getDirection() == null : this.direction.equals(((UniversalUserSort) obj).getDirection()))
                && (sortBy == null ? ((UniversalUserSort) obj).getSortBy() == null : this.sortBy.equals(((UniversalUserSort) obj).getSortBy())));
    }
}
