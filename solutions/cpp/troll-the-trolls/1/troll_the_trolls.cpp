namespace hellmath {

    enum class AccountStatus {
        troll,
        guest,
        user,
        mod,
    };

    enum class Action {
        read,
        write,
        remove,
    };

    bool display_post(AccountStatus status1, AccountStatus status2) {
        return (status1 == status2 && status1 == AccountStatus::troll) || status1 != AccountStatus::troll;
    }

    bool permission_check(Action action, AccountStatus account) {
        bool have_permission{false};

        switch (action) {
        case Action::read:
            have_permission = true;
            break;
        case Action::write:
            have_permission = account != AccountStatus::guest;
            break;
        case Action::remove:
            have_permission = account == AccountStatus::mod;
            break;
        }
        
        return have_permission;
    }

    bool valid_player_combination(AccountStatus status1, AccountStatus status2) {
        bool valid{false};
        switch(status1) {
        case AccountStatus::guest: break;
        case AccountStatus::troll: 
            valid = status2 == AccountStatus::troll;  
            break;
        case AccountStatus::user:
        case AccountStatus::mod:
            valid = status2 != AccountStatus::guest && status2 != AccountStatus::troll;
            break;
        }
        return valid;
    }

    bool has_priority(AccountStatus status1, AccountStatus status2) {
        return status1 > status2;
    }

}  // namespace hellmath
