package org.example.VkApi;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.enums.GroupsSort;
import com.vk.api.sdk.objects.groups.Fields;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.SearchSort;
import com.vk.api.sdk.objects.groups.responses.GetByIdLegacyResponse;
import com.vk.api.sdk.objects.groups.responses.GetByIdObjectLegacyResponse;

public class VkRepository {
    private final int APP_ID =
            0; // 0
    private final String CODE =
            "vk.token"; // "vk.token"
    private final VkApiClient vk;
    private final UserActor actor;

    public VkRepository() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public Group getMostPopularGroupByUser(String player) throws ClientException, ApiException {
        return vk.groups().search(actor, player)
                .sort(SearchSort.ATTENDANCE)
                .count(1)
                .execute()
                .getItems()
                .stream()
                .findFirst()
                .orElse(new Group());
    }
    public String getGroupName(Group group)  throws ClientException, ApiException {
        if (group.getId() == null) return "Nan group";

        return group.getName();
    }
    public int getGroupMembersCount(Group group) throws ClientException, ApiException {
        if (group.getId() == null) return 0;

        return vk.groups().getByIdObjectLegacy(actor)
                .groupId(group.getId().toString())
                .fields(Fields.MEMBERS_COUNT)
                .execute()
                .stream()
                .findFirst()
                .orElse(new GetByIdObjectLegacyResponse())
                .getMembersCount();
    }
}
