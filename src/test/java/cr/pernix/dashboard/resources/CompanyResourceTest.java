package cr.pernix.dashboard.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import cr.pernix.dashboard.models.Company;
import cr.pernix.dashboard.services.CompanyService;

public class CompanyResourceTest extends JerseyTest {

    private final String NAME = "Pernix";

    private CompanyService companyService = CompanyService.getInstance();

    private List<Company> insertTestCompanies(int count) {
        List<Company> testCompanies = new ArrayList<>();
        for (; count > 0; count--) {
            Company testCompany = new Company();
            testCompany.setName(NAME);
            companyService.save(testCompany);
            testCompanies.add(testCompany);
        }
        return testCompanies;
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(CompanyResource.class);
    }

    @Test
    public void testGetAll() {
        List<Company> testUser = insertTestCompanies(5);
        Assert.assertTrue(testUser.size() == 5);
        final Response response = target().path("company").request().get();
        Assert.assertEquals(200, response.getStatus());
        List<Company> companyList = response.readEntity(new GenericType<List<Company>>() {
        });
        Assert.assertEquals(testUser.size(), companyList.size());
        for(Company company: companyList) {
            companyService.delete(company.getId());
        }
    }

    @Test
    public void testGet() {
        List<Company> testCompany = insertTestCompanies(1);
        Assert.assertTrue(testCompany.size() > 0);
        Company toCompare = testCompany.get(0);
        String path = "company/%d";
        final Response response = target().path(String.format(path, toCompare.getId())).request().get();
        Assert.assertEquals(200, response.getStatus());
        Company company = response.readEntity(Company.class);
        Assert.assertTrue("Object do not match", company.equals(toCompare));
        companyService.delete(toCompare.getId());
    }

    @Test
    public void testDelete() {
        List<Company> testCompany = insertTestCompanies(1);
        Assert.assertTrue(testCompany.size() > 0);
        Company toDelete = testCompany.get(0);
        String path = "company/%d";
        final Response response = target().path(String.format(path, toDelete.getId())).request().delete();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNull("Object still persist", companyService.get(toDelete.getId()));
    }

    @Test
    public void testEdit() {
        List<Company> testCompany = insertTestCompanies(1);
        Assert.assertTrue(testCompany.size() > 0);
        Company toUpdate = testCompany.get(0);
        toUpdate.setName("Modified Name");
        final Response response = target().path("company").request().put(Entity.json(toUpdate), Response.class);
        Assert.assertEquals(200, response.getStatus());
        Company modifiedCompany = CompanyService.getInstance().get(toUpdate.getId());
        Assert.assertTrue("Not the same object", modifiedCompany.equals(toUpdate));
        Assert.assertNotEquals("Name not modified", NAME, modifiedCompany.getName());
        companyService.delete(toUpdate.getId());
    }
}
