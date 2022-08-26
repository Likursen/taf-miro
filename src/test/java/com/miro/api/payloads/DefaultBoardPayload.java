package com.miro.api.payloads;

public class DefaultBoardPayload {
    private String name = "Untitled";
    private Policy policy = new Policy();


    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public String getName() {
        return name;
    }

    public DefaultBoardPayload setName(String name) {
        this.name = name;
        return this;
    }

    public static class Policy {
        private PermissionsPolicy permissionsPolicy = new PermissionsPolicy();
        private SharingPolicy sharingPolicy = new SharingPolicy();

        public void setPermissionsPolicy(PermissionsPolicy permissionsPolicy) {
            this.permissionsPolicy = permissionsPolicy;
        }

        public SharingPolicy getSharingPolicy() {
            return sharingPolicy;
        }

        public void setSharingPolicy(SharingPolicy sharingPolicy) {
            this.sharingPolicy = sharingPolicy;
        }

        public PermissionsPolicy getPermissionsPolicy() {
            return permissionsPolicy;
        }

        public static class PermissionsPolicy {
            private String collaborationToolsStartAccess = "all_editors";
            private String copyAccess = "anyone";
            private String sharingAccess = "team_members_with_editing_rights";

            public void setCollaborationToolsStartAccess(String collaborationToolsStartAccess) {
                this.collaborationToolsStartAccess = collaborationToolsStartAccess;
            }

            public String getCopyAccess() {
                return copyAccess;
            }

            public void setCopyAccess(String copyAccess) {
                this.copyAccess = copyAccess;
            }

            public String getSharingAccess() {
                return sharingAccess;
            }

            public void setSharingAccess(String sharingAccess) {
                this.sharingAccess = sharingAccess;
            }

            public String getCollaborationToolsStartAccess() {
                return collaborationToolsStartAccess;
            }
        }

        public static class SharingPolicy {
            private String access = "private";
            private String inviteToAccountAndBoardLinkAccess = "no_access";
            private String organizationAccess = "private";
            private String teamAccess = "private";

            public void setAccess(String access) {
                this.access = access;
            }

            public String getInviteToAccountAndBoardLinkAccess() {
                return inviteToAccountAndBoardLinkAccess;
            }

            public void setInviteToAccountAndBoardLinkAccess(String inviteToAccountAndBoardLinkAccess) {
                this.inviteToAccountAndBoardLinkAccess = inviteToAccountAndBoardLinkAccess;
            }

            public String getOrganizationAccess() {
                return organizationAccess;
            }

            public void setOrganizationAccess(String organizationAccess) {
                this.organizationAccess = organizationAccess;
            }

            public String getTeamAccess() {
                return teamAccess;
            }

            public void setTeamAccess(String teamAccess) {
                this.teamAccess = teamAccess;
            }

            public String getAccess() {
                return access;
            }
        }

    }
}