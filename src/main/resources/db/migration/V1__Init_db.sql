create table dental_formula
(
    id_dental_formula      integer not null auto_increment,
    tooth                  varchar(255),
    problems               varchar(255),
    patient_dental_formula integer,
    primary key (id_dental_formula)
) engine = InnoDB;
create table doctors
(
    id_doctor  integer not null auto_increment,
    name       varchar(255),
    patronymic varchar(255),
    surname    varchar(255),
    primary key (id_doctor)
) engine = InnoDB;
create table mkb_codes
(
    id_mkb_code             integer not null auto_increment,
    clinical_diagnosis_code integer,
    prelim_diagnosis_code   integer,
    primary key (id_mkb_code)
) engine = InnoDB;
create table objective_survey_data
(
    id_objective_survey_data integer not null auto_increment,
    conscience               varchar(255),
    face                     varchar(255),
    health                   varchar(255),
    open_mouth               varchar(255),
    skin_covers              varchar(255),
    primary key (id_objective_survey_data)
) engine = InnoDB;
create table patients
(
    id_patient                  integer not null auto_increment,
    access                      bit,
    address                     varchar(255),
    clinical_diagnosis          varchar(255),
    complaints                  varchar(255),
    concomitant_diseases        varchar(255),
    condition_lymph_nodes       varchar(255),
    condition_teeth_periodontal varchar(255),
    date_of_access              varchar(255),
    date_of_birth               varchar(255),
    dev_real_disease            varchar(255),
    diagnosis                   varchar(255),
    email                       varchar(255),
    ex_vestibule_oral_cavity    varchar(255),
    hygiene_index               integer,
    i_n_n                       bigint,
    mobile_number               bigint,
    name                        varchar(255),
    occlusion                   varchar(255),
    patronymic                  varchar(255),
    prelim_diagnosis            varchar(255),
    serial_number_passport      bigint,
    sex                         varchar(255),
    surname                     varchar(255),
    survey_data                 varchar(255),
    survey_plan                 varchar(255),
    treatment_plan              varchar(255),
    treatment_protocol          varchar(255),
    id_objective_survey_data    integer,
    id_mkb_code                 integer,
    primary key (id_patient)
) engine = InnoDB;
create table schedules
(
    id_schedule          integer not null auto_increment,
    date_time_of_receipt datetime(6),
    id_doctor            integer,
    id_patient           integer,
    primary key (id_schedule)
) engine = InnoDB;
alter table dental_formula
    add constraint patient_df_fk foreign key (patient_dental_formula) references patients (id_patient);
alter table patients
    add constraint patient_osd_fk foreign key (id_objective_survey_data) references objective_survey_data (id_objective_survey_data);
alter table patients
    add constraint patient_mkb_fk foreign key (id_mkb_code) references mkb_codes (id_mkb_code);
alter table schedules
    add constraint schedule_doctor_fk foreign key (id_doctor) references doctors (id_doctor);
alter table schedules
    add constraint schedule_patient_fk foreign key (id_patient) references patients (id_patient);