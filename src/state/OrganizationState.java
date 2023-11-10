package state;

import entity.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Organization findOrganization(Integer organization) {
        var list = organizationsManaged;
        for (Organization item:
                list) {
            if (Objects.equals(item.getId(), organization)) {
                return item;
            }
        }
        return new Organization();
    }

    public void removeOrganization(Integer organization) {
        for (Organization item:
                organizationsManaged) {
            if (Objects.equals(item.getId(), organization)) {
                organizationsManaged.remove(item);
                return;
            }
        }
    }
}
