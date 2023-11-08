package state;

import entity.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrganizationState extends State{
    List<Organization> organizationsJoined;
    List<Organization> organizationsManaged;


    private static final OrganizationState INSTANCE = new OrganizationState();

    private OrganizationState() {
        organizationsJoined = new ArrayList<>();
        organizationsManaged = new ArrayList<>();
    }

    public static OrganizationState getInstance() { return INSTANCE; }

    public void addOrganizationManaged(Organization organization) {
        organizationsManaged.add(0, organization);
    }
}
