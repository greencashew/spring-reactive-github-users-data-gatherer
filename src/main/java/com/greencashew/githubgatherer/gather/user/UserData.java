package com.greencashew.githubgatherer.gather.user;

record UserData(long id,
                String login,
                String name,
                String type,
                String avatar_url,
                String created_at,
                int followers,
                int public_repos) {
    private static final float MAXIMUM_SCORE = 6;

    float getScore() {
        return MAXIMUM_SCORE / followers * (2 + public_repos);
    }
}
