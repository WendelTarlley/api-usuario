create table usuario (
 id uuid DEFAULT uuid_generate_v4 (),
 nome varchar not null,
 cpf varchar not null,
 email varchar not null,
 senha varchar not null
)