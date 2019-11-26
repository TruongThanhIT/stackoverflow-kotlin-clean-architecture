# kotlin-clean-architecture
StackOverFlow User

1. StackOverFlow User List:
● List out all of SOF user list. User can able to scroll down to see all of SOF user.
● User can bookmark / de-bookmark a SOF user.
● Provide option for user to see only SOF user that has been bookmarked.
● When the app is restarted, all bookmark setting must be maintained.
API: GET https://api.stackexchange.com/2.2/users?page=1&pagesize=30&site=stackoverflow
2. SOF User Reputation
● User can click on any SOF user to see their detail reputation.
API: GET https://api.stackexchange.com/2.2/users/{user_id}/reputation-history?page=1&pagesize=30&site=stackoverflow

