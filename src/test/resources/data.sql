-- ACCOUNT 테이블 초기화 데이터
INSERT INTO account (
    account_id, account_password, account_name, account_nickname, account_sex,
    account_age, account_phone_number, account_registered_datetime, account_profile_image,
    account_email, account_star_rating, account_work_count, account_deleted, created_at, updated_at
) VALUES
      ('user01', 'pass01', 'Alice', 'test1', 'F', 25, '010-1234-5678', CURRENT_TIMESTAMP, 'profile1.jpg', 'alice@example.com', 5,1,false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('user02', 'pass02', 'Bob', 'test2', 'M', 30, '010-2345-6789', CURRENT_TIMESTAMP, 'profile2.jpg', 'bob@example.com', 4.5,2,false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- CATEGORY 테이블 초기화 데이터
INSERT INTO category (
    category_name, created_at, updated_at
) VALUES
      ('Technology', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('Healthcare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- BUSINESS 테이블 초기화 데이터
INSERT INTO business (
    business_employer_id, business_name, business_location, representation_name,
    business_open_date, business_registration_number, created_at, updated_at
) VALUES
    ( 1, 'Tech Solutions', 'Seoul', 'Alice Kim', '2020-01-15', '123-45-67890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- BUSINESS_CATEGORY 테이블 초기화 데이터
INSERT INTO business_category (
    business_id, categorey_id, created_at, updated_at
) VALUES
      (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ( 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ANNOUNCEMENT 테이블 초기화 데이터
INSERT INTO announcement (
    announcement_title, announcement_type, announcement_content, view_count, created_at, updated_at
) VALUES
    ('New Policy', 'HR', 'Please review the new company policy.', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- OFFER_EMPLOYMENT 테이블 초기화 데이터
INSERT INTO offer_employment (
    suggest_hourly_pay, suggest_readed, suggest_succeded, business_id, employee_id,
    suggest_register_time, suggest_start_time, suggest_end_time
) VALUES
    (20000, false, false, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- CHAT_ROOM 테이블 초기화 데이터
-- INSERT INTO chat_room (
--     suggest_generated_date, suggest_id
-- ) VALUES
--     (CURRENT_TIMESTAMP, 1);

-- CONTRACT 테이블 초기화 데이터
INSERT INTO contract (
    contract_hourly_pay, contract_succeded, contract_start_time, contract_end_time, suggest_id, created_at, updated_at
) VALUES
    ( 25000, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- EXTERNAL_CAREER 테이블 초기화 데이터
INSERT INTO exteranl_carrer (
    employee_id, business_name, part_time_period, created_at, updated_at
) VALUES
    (1, 'Part-time Developer', '2019-2020', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- FLAVORED 테이블 초기화 데이터
INSERT INTO flavored (
    category_id, employee_id, created_at, updated_at
) VALUES
    ( 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- POSSIBLE_BOARD 테이블 초기화 데이터
INSERT INTO possible_board (
    employee_id, possible_start_time, possible_end_time, created_at, updated_at
) VALUES
    (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- REVIEW 테이블 초기화 데이터
INSERT INTO review (
    review_star_point, review_content, created_at, updated_at
) VALUES
    (5, 'Excellent service!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- SCRAP 테이블 초기화 데이터
INSERT INTO scrap (
    employee_id, employer_id, created_at, updated_at
) VALUES
    ( 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
