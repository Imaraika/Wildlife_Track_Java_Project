import spark.ModelAndView;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("Angesanimals", Animal.all());
            model.put("AngesendangeredAnimals", EndangeredAnimal.all());
            model.put("Angessightings", Sighting.all());
            model.put("templateAnge", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/endangered_sighting", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
            String latLong = request.queryParams("latLong");
            Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", EndangeredAnimal.all());
            String animal = EndangeredAnimal.find(animalIdSelected).getName();
            model.put("animal", animal);
            model.put("templateAnge", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sighting", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
            String latLong = request.queryParams("latLong");
            Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", Animal.all());
            String animal = Animal.find(animalIdSelected).getName();
            model.put("animal", animal);
            model.put("templateAnge", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animal/new", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("templateAnge", "templates/animal-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            boolean endangered = request.queryParamsValues("endangered")!=null;
            if (endangered) {
                String name = request.queryParams("name");
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
                endangeredAnimal.save();
                model.put("animals", Animal.all());
                model.put("endangeredAnimals", EndangeredAnimal.all());
            } else {
                String name = request.queryParams("name");
                Animal animal = new Animal(name);
                animal.save();
                model.put("animals", Animal.all());
                model.put("endangeredAnimals", EndangeredAnimal.all());
            }
            response.redirect("/");
            return null;
        });

        get("/animal/:id", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal animal = Animal.find(Integer.parseInt(request.params("id")));
            model.put("animal", animal);
            model.put("templateAnge", "templates/animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/endangered_animal/:id", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
            model.put("endangeredAnimal", endangeredAnimal);
            model.put("templateAnge", "templates/endangered-animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/error", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("templateAnge", "templates/error.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
