package Resources;

import Create.User;
import Pojo.ApiResource;
import Pojo.Support;
import Pojo.UserDataResponse;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    public ApiResource payload()
    {
        ApiResource a = new ApiResource();
        a.setPage(1);
        a.setPer_page(6);
        a.setTotal(2);
        a.setTotal_pages(2);

        List<UserDataResponse> userDataList = new ArrayList<>();


        UserDataResponse user1 = new UserDataResponse();
        user1.setId(1);
        user1.setEmail("george.bluth@reqres.in");
        user1.setFirst_name("George");
        user1.setLast_name("Bluth");
        user1.setAvatar("https://reqres.in/img/faces/1-image.jpg");
        userDataList.add(user1);

        UserDataResponse user2 = new UserDataResponse();
        user2.setId(2);
        user2.setEmail("janet.weaver@reqres.in");
        user2.setFirst_name("Janet");
        user2.setLast_name("Weaver");
        user2.setAvatar("https://reqres.in/img/faces/2-image.jpg");
        userDataList.add(user2);

        UserDataResponse user3 = new UserDataResponse();
        user3.setId(3);
        user3.setEmail("emma.wong@reqres.in");
        user3.setFirst_name("Emma");
        user3.setLast_name("Wong");
        user3.setAvatar("https://reqres.in/img/faces/3-image.jpg");
        userDataList.add(user3);

        UserDataResponse user4 = new UserDataResponse();
        user4.setId(4);
        user4.setEmail("eve.holt@reqres.in");
        user4.setFirst_name("Eve");
        user4.setLast_name("Holt");
        user4.setAvatar("https://reqres.in/img/faces/4-image.jpg");
        userDataList.add(user4);

        UserDataResponse user5 = new UserDataResponse();
        user5.setId(5);
        user5.setEmail("charles.morris@reqres.in");
        user5.setFirst_name("Charles");
        user5.setLast_name("Morris");
        user5.setAvatar("https://reqres.in/img/faces/5-image.jpg");
        userDataList.add(user5);

        UserDataResponse user6 = new UserDataResponse();
        user6.setId(6);
        user6.setEmail("tracey.ramos@reqres.in");
        user6.setFirst_name("Tracey");
        user6.setLast_name("Ramos");
        user6.setAvatar("https://reqres.in/img/faces/6-image.jpg");
        userDataList.add(user6);

        a.setData(userDataList);



        Support s = new Support();
        s.setText("https://reqres.in/#support-heading");
        s.setUrl("To keep ReqRes free, contributions towards server costs are appreciated!");
        a.setSupport(s);

        return a;


    }
    public static User buildUserPayload(String name, String Email) {

        User user = new User();
        user.setName(name);
        user.setJob(Email);
        return  user;
    }
}
