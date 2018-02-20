create table assignments (
       ticket_id bigint not null,
        technician_id bigint not null
    ) engine=InnoDB;

create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table Ticket_updates (
       Ticket_id bigint not null,
       lastUpdate_dt datetime,
       modifiedDate varchar(255),
       modifier varchar(255),
       updateDetails varchar(255)
    ) engine=InnoDB;

create table tickets (
       id bigint not null,
        completion_details varchar(255),
        details varchar(255),
        end_dt datetime,
        location varchar(255),
        priority varchar(255),
        progress varchar(255),
        start_dt datetime,
        subject varchar(255),
        requesterDetails_id bigint,
        unit_id bigint,
        primary key (id)
    ) engine=InnoDB;

create table units (
       id bigint not null,
        description varchar(255) not null,
        email varchar(255) not null,
        location varchar(255) not null,
        name varchar(255) not null,
        phone varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

create table users (
       id bigint not null,
        email varchar(255),
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        phone varchar(255),
        post varchar(255),
        username varchar(255) not null,
        unit_id bigint,
        primary key (id)
    ) engine=InnoDB;

alter table units 
       add constraint UK_etw07nfppovq9p7ov8hcb38wy unique (name);

alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

alter table assignments 
       add constraint FK8mhcham1p9eg34kjjukrysfec 
       foreign key (technician_id) 
       references users (id);

alter table assignments 
       add constraint FKo9wcactgruf2l6m8tw49vm7kv 
       foreign key (ticket_id) 
       references tickets (id);

alter table Ticket_updates 
       add constraint FKk0iy7c8k1ht1tfp76ul5092o7 
       foreign key (modifier) 
       references users (username);

alter table Ticket_updates 
       add constraint FK8c97v8b7g3qfpykcgdu9bpfy 
       foreign key (Ticket_id) 
       references tickets (id);

alter table tickets 
       add constraint FKo5pcvdw2fctifmrgvohqfcef6 
       foreign key (requesterDetails_id) 
       references users (id);

alter table tickets 
       add constraint FKmj126vcy9uobxd6rfu269wjc2 
       foreign key (unit_id) 
       references units (id);

alter table users 
       add constraint FKp2hfld4bhbwtakwrmt4xq6een 
       foreign key (unit_id) 
       references units (id);

       
insert into users (id, first_name, last_name, username, password) values (1, 'admin', 'admin', 'admin', 'abcd');
insert into users (id, first_name, last_name, username, password) values (2, 'cysun', 'cysun', 'cysun', 'abcd');

