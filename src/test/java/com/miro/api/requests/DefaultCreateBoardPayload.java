package com.miro.api.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultCreateBoardPayload {
    public String name = "Untitled";
    public Policy policy = new Policy();

    @Getter
    @Setter
    public class Policy {
        public PermissionsPolicy permissionsPolicy = new PermissionsPolicy();
        public SharingPolicy sharingPolicy = new SharingPolicy();
    }

    @Getter
    @Setter
    public class PermissionsPolicy {
        public String collaborationToolsStartAccess = "all_editors";
        public String copyAccess = "anyone";
        public String sharingAccess = "team_members_with_editing_rights";
    }

    @Getter
    @Setter
    public class SharingPolicy {
        public String access = "private";
        public String inviteToAccountAndBoardLinkAccess = "no_access";
        public String organizationAccess = "private";
        public String teamAccess = "private";
    }
}