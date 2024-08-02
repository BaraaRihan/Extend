package StepDefinition;

public enum ApiResources {


    Get("/users"),
    Put("/users/2"),
    Post("/users"),
    Delete("/users/5");

    private String resource;

    ApiResources(String resource) {
        this.resource=resource;
    }

    public  String getResource()
    {
        return resource;
    }
}
