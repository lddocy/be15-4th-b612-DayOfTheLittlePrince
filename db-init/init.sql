GRANT ALL PRIVILEGES ON little_prince.* TO 'b612'@'%';

USE little_prince;

# DROP TABLE IF EXISTS `notification`;
# DROP TABLE IF EXISTS `task`;
# DROP TABLE IF EXISTS `project`;
# DROP TABLE IF EXISTS `exp_history`;
# DROP TABLE IF EXISTS `get_badge`;
# DROP TABLE IF EXISTS `badge`;
# DROP TABLE IF EXISTS `notification_type`;
# DROP TABLE IF EXISTS `review`;
# DROP TABLE IF EXISTS `get_item`;
# DROP TABLE IF EXISTS `item`;
# DROP TABLE IF EXISTS `member`;
# DROP TABLE IF EXISTS `famous_quotes`;

CREATE TABLE `member` (
                          `member_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '회원ID',
                          `email` VARCHAR(30) NOT NULL UNIQUE COMMENT '이메일',
                          `password` VARCHAR(255) NOT NULL COMMENT '비밀번호',
                          `is_deleted` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '탈퇴 여부',
                          `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '생성날짜',
                          `exp` INT NOT NULL DEFAULT 0 COMMENT '경험치',
                          `level` INT NOT NULL DEFAULT 0 COMMENT '레벨',
                          `planet_name` VARCHAR(255) NOT NULL DEFAULT '외로운 행성' COMMENT '행성이름',
                          `is_notification_enabled`	ENUM('Y','N')	NOT NULL	DEFAULT 'N'	COMMENT '알림설정여부',
                          PRIMARY KEY (`member_id`)
);

CREATE TABLE `item` (
                        `item_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '아이템 ID',
                        `item_name` VARCHAR(100) NOT NULL COMMENT '아이템 이름',
                        `x_coord` DECIMAL(15, 8) NOT NULL COMMENT 'X좌표',
                        `y_coord` DECIMAL(15, 8) NOT NULL COMMENT 'Y좌표',
                        `z_coord` DECIMAL(15, 8) NOT NULL COMMENT 'Z좌표',
                        `level` INT NOT NULL COMMENT '획득 레벨',
                        `size` DECIMAL(15, 8) NOT NULL COMMENT '아이템 크기',
                        `is_fixed` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '고정여부',
                        PRIMARY KEY (`item_id`)
);

CREATE TABLE `get_item` (
                            `member_id` BIGINT NOT NULL COMMENT '회원ID',
                            `item_id` BIGINT NOT NULL COMMENT '아이템 ID',
                            `is_hidden` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '숨김여부',
                            `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '생성 날짜',
                            PRIMARY KEY (`member_id`, `item_id`),
                            FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
                            FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
);

CREATE TABLE `review` (
                          `review_id` INT NOT NULL AUTO_INCREMENT COMMENT '회고ID',
                          `member_id` BIGINT NOT NULL COMMENT '회원ID',
                          `review_content` VARCHAR(100) NOT NULL COMMENT '회고 내용',
                          `review_date` DATE NOT NULL COMMENT '날짜',
                          `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '생성 날짜',
                          PRIMARY KEY (`review_id`)
);

CREATE TABLE `notification_type` (
                                     `notification_type` BIGINT NOT NULL AUTO_INCREMENT COMMENT '유형ID',
                                     `template` VARCHAR(255) NOT NULL COMMENT '템플릿',
                                     PRIMARY KEY (`notification_type`)
);

CREATE TABLE `badge` (
                         `badge_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '칭호ID',
                         `badge_name` VARCHAR(100) NOT NULL COMMENT '칭호이름',
                         `badge_mean` VARCHAR(255) NOT NULL COMMENT '칭호의미',
                         `level` INT NOT NULL COMMENT '획득레벨',
                         PRIMARY KEY (`badge_id`)
);

CREATE TABLE `get_badge` (
                             `badge_id` BIGINT NOT NULL COMMENT '칭호ID',
                             `member_id` BIGINT NOT NULL COMMENT '회원ID',
                             `is_selected` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '선택여부',
                             `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '생성 날짜',
                             PRIMARY KEY (`badge_id`, `member_id`),
                             FOREIGN KEY (`badge_id`) REFERENCES `badge` (`badge_id`),
                             FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
);

CREATE TABLE `project` (
                           `project_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '장기ID',
                           `member_id` BIGINT NOT NULL COMMENT '회원ID',
                           `title` VARCHAR(100) NOT NULL COMMENT '제목',
                           `start_date` DATE NOT NULL COMMENT '시작날짜',
                           `end_date` DATE NOT NULL COMMENT '종료날짜',
                           `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '생성 날짜',
                           PRIMARY KEY (`project_id`)
);

CREATE TABLE `task` (
                        `task_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '단기ID',
                        `project_id` BIGINT NULL COMMENT '장기ID',
                        `member_id` BIGINT NOT NULL COMMENT '회원ID',
                        `content` VARCHAR(255) NOT NULL COMMENT '내용',
                        `is_checked` ENUM('N','Y') NOT NULL DEFAULT 'N' COMMENT '체크여부',
                        `date` DATE NOT NULL COMMENT '일자',
                        `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '생성 날짜',
                        PRIMARY KEY (`task_id`),
                        FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE
);

CREATE TABLE `exp_history` (
                               `history_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '이력ID',
                               `project_id`	BIGINT	NULL	COMMENT '장기ID',
                               `member_id` BIGINT NOT NULL COMMENT '회원ID',
                               `exp_point` INT NOT NULL COMMENT '획득경험치',
                               `created_at` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '경험치 획득일',
                               PRIMARY KEY (`history_id`),
                               FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
                               FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
);

create table notification
(
    notification_id int auto_increment comment '알림ID'
        primary key,
    category_id     bigint                                not null comment '유형ID',
    member_id       bigint                                not null comment '회원ID',
    created_at      timestamp default current_timestamp() not null comment '생성일시',
    is_read         ENUM('N','Y') NOT NULL DEFAULT 'N' COMMENT '읽음처리',
    constraint fk_notification_type
        foreign key (category_id) references notification_type (notification_type)
);

CREATE TABLE `famous_quotes` (
                                 `famous_quotes_id`	BIGINT	NOT NULL AUTO_INCREMENT	COMMENT '명대사 ID',
                                 `title`	VARCHAR(1024)	NOT NULL	COMMENT '대사',
                                 PRIMARY KEY (`famous_quotes_id`)
);

CREATE TABLE web_push_subscription (
                                       member_id BIGINT NOT NULL PRIMARY KEY,
                                       endpoint TEXT NOT NULL,
                                       p256dh TEXT NOT NULL,
                                       auth TEXT NOT NULL,
                                       CONSTRAINT fk_member
                                           FOREIGN KEY (member_id) REFERENCES member(member_id)
                                               ON DELETE CASCADE
);

INSERT INTO `badge` (`badge_name`, `badge_mean`, `level`)
VALUES
    ('이름 없음', '칭호 없음', 0),
    ('조용한 별빛', '조용히 시작하는 첫 마음', 1),
    ('맑은 장미', '작고 여린, 하지만 소중한 존재', 2),
    ('꾸준한 여우', '신뢰와 습관이 피어나는 단계', 3),
    ('성실한 여행자', '성실함이 여정이 되기 시작', 4),
    ('다정한 조종사', '사명감과 애정을 가진 여정', 5),
    ('깊은 질문가', '세상과 자신에게 질문을 던지는 시간', 6),
    ('생각하는 어린왕자', '존재와 관계에 대한 깊이 있는 성찰', 7),
    ('지혜로운 행성지기', '나의 별, 나의 책임을 성실히 돌보는 사람', 8),
    ('온전한 친구', '진심 어린 관계와 꾸준함의 결정체', 9),
    ('영원의 별의 수호자', '모든 것을 품은 완성형, 최고의 성숙과 헌신', 10);

INSERT INTO item (item_name, x_coord, y_coord, z_coord, `level`, `size`, is_fixed) VALUES
                                                                                       ('행성', 0, -15, 0, 0, 0.6, 'Y'),
                                                                                       ('여우', 8, 9.6, 5, 1, 0.01, 'Y'),
                                                                                       ('우물', 11, 5.5, 5, 2, 0.5, 'Y'),
                                                                                       ('와인', 10, 1, -5, 3, 1.5, 'Y'),
                                                                                       ('양', -5, 5, 9, 4, 2, 'Y'),
                                                                                       ('꽃', -6, -2, -2, 5, 4, 'Y'),
                                                                                       ('바오밥나무', -2, 4, -3, 6, 0.5, 'Y'),
                                                                                       ('비행기', 18, 15, -1, 7, 3, 'Y'),
                                                                                       ('푸른 행성', 30, 20, -15, 8, 5, 'Y'),
                                                                                       ('왕의 행성', -25, 20, -20, 9, 1, 'Y'),
                                                                                       ('왕관', 5.3, 11, 0.5, 10, 0.9, 'Y');

INSERT INTO `famous_quotes` (`title`)
VALUES
    ('부탁이 있는데요, 양 한 마리만 그려줘'),
    ('가장 중요한 건 눈에 보이지 않아'),
    ('모든 어른들은 한때 어린이였다. 그러나 그것을 기억하는 어른은 별로 없다.'),
    ('어른들은 숫자를 좋아한다.'),
    ('너는 나에게 이 세상에 단 하나 뿐인 존재가 되는 거고, 나도 너에게 세상에 하나 뿐인 존재가 되는 거야.'),
    ('넌 네가 길들인 것에 대해 언제까지나 책임이 있어.'),
    ('별들은 아름다워. 보이지 않는 한 송이 꽃 때문에.'),
    ('사막이 아름다운 것은 어딘가에 샘을 감추고 있기 때문이야.'),
    ('누군가에게 길들여진다는 것은 눈물을 흘릴 일이 생긴다는 것일지도 모른다.'),
    ('사람들 속에서도 외로운 건 마찬가지야.'),
    ('내가 죽은 것처럼 보일 거야. 하지만 그게 아니야.'),
    ('나를 길들여 줘… 부탁이야!'),
    ('만약 오후 4시에 네가 온다면, 나는 3시부터 행복해지기 시작할 거야.'),
    ('어린 왕자: 그렇다면 이건 너에게 전혀 좋은 게 아니잖아! 여우: 나에게 이건 좋은 거야, 왜냐하면 이제 나에겐 밀밭의 색깔이 있잖아.'),
    ('잘못 생각하고 있어. 나는 내가 소유한 것들이 아니라, 내가 돌보고 있는 것들 뿐이야.'),
    ('사람들은 별들을 그려내고, 그것을 우리가 보는 별이라고 한다. 그러나 그 별은 단지 그림 뿐이야.'),
    ('내 마음속에 네가 살아있어. 그래서 나는 외로워하지 않아.'),
    ('그대는 그대의 장미꽃을 좋아하는데, 그것은 그대가 가장 잘 알고 있기 때문이지.'),
    ('같은 것을 반복하면 늘 같은 결과를 얻게 된다.'),
    ('나의 장미꽃은 나에게 오직 하나 뿐이다. 그리고 그것을 내가 지켜야만 한다.'),
    ('사랑이란 서로를 바라보는 것이 아니라, 같은 방향을 바라보는 거야.'),
    ('길들인다는 건 관계를 맺는다는 뜻이야.'),
    ('중요한 건 마음으로 보아야 해. 본질은 눈에 보이지 않거든.'),
    ('네 장미가 그토록 소중한 이유는, 네가 그 장미에게 들인 시간 때문이야.'),
    ('어떤 비밀이야. 가장 중요한 건 마음으로 보아야 해.'),
    ('별을 보는 사람마다 다른 의미를 가져. 여행자에게는 길잡이, 다른 사람에겐 단순한 빛.'),
    ('시간을 헛되이 보내지 않으려면, 마음의 눈을 떠야 해.'),
    ('사람들은 너무 바빠서 중요한 걸 놓치고 있어.'),
    ('내 별은 너무 작아서 네가 보지 못해. 하지만 그 별은 분명히 있어.'),
    ('너는 수많은 장미들 사이에서도 너의 장미를 알아볼 수 있을 거야.'),
    ('내가 진짜 필요했던 건 물이 아니라, 마음을 적셔 줄 무언가였어.');

