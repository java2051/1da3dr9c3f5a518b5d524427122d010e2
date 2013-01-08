DROP table if EXISTS "table" ;
CREATE TABLE "table" (
	"id1" int8,
	"id2" int8,
	"field_a" int2 DEFAULT 10,
	"field_b" smallint not NULL,
	"field_c" int4 ,
	"field_d" integer ,
	"field_e" oid ,
	"field_f" SERIAL ,
	"field_g" int8 ,
	"field_h" bigint ,
	"field_i" bigserial ,
	"field_j" money ,
	"field_k" numeric ,
	"field_l" float4 ,
	"field_m" float8 ,
	"field_n" real ,
	"field_q" bpchar(3) DEFAULT 'abc',
	"field_r" varchar(32) DEFAULT 'ABCDEFG' ,
	"field_s" char ,
	"field_t" text ,
	"field_u" name ,
	"field_v" bytea ,
	"field_w" bool ,
	"field_x" boolean ,
	"field_y" bit ,
	"field_z" date ,
	"field_0a" time ,
	"field_0b" timetz ,
	"field_0c" timestamp ,
	"field_0d" timestamptz,
  PRIMARY KEY ("id1", "id2"),
  UNIQUE ("field_a"),
  UNIQUE ("field_b")
) ;
COMMENT ON TABLE "table" IS 'Test comment of table ';


COMMENT ON COLUMN "table"."id1" IS '第一主键';
COMMENT ON COLUMN "table"."id2" IS '第二主键';
COMMENT ON COLUMN "table"."field_a" IS 'Test column comment int2';
COMMENT ON COLUMN "table"."field_b" IS 'Test column comment smallint';
COMMENT ON COLUMN "table"."field_c" IS 'Test column comment int4 ';
COMMENT ON COLUMN "table"."field_d" IS 'Test column comment integer ';
COMMENT ON COLUMN "table"."field_e" IS 'Test column comment oid ';
COMMENT ON COLUMN "table"."field_f" IS 'Test column comment serial ';
COMMENT ON COLUMN "table"."field_g" IS 'Test column comment int8 ';
COMMENT ON COLUMN "table"."field_h" IS 'Test column comment bigint ';
COMMENT ON COLUMN "table"."field_i" IS 'Test column comment bigserial ';
COMMENT ON COLUMN "table"."field_j" IS 'Test column comment money ';
COMMENT ON COLUMN "table"."field_k" IS 'Test column comment numeric ';
COMMENT ON COLUMN "table"."field_l" IS 'Test column comment float4 ';
COMMENT ON COLUMN "table"."field_m" IS 'Test column comment float8 ';
COMMENT ON COLUMN "table"."field_n" IS 'Test column comment real ';
COMMENT ON COLUMN "table"."field_q" IS 'Test column comment bpchar';
COMMENT ON COLUMN "table"."field_r" IS 'Test column comment varchar ';
COMMENT ON COLUMN "table"."field_s" IS 'Test column comment char ';
COMMENT ON COLUMN "table"."field_t" IS 'Test column comment text ';
COMMENT ON COLUMN "table"."field_u" IS 'Test column comment name ';
COMMENT ON COLUMN "table"."field_v" IS 'Test column comment bytea ';
COMMENT ON COLUMN "table"."field_w" IS 'Test column comment bool ';
COMMENT ON COLUMN "table"."field_x" IS 'Test column comment boolean ';
COMMENT ON COLUMN "table"."field_y" IS 'Test column comment bit ';
COMMENT ON COLUMN "table"."field_z" IS 'Test column comment date ';
COMMENT ON COLUMN "table"."field_0a" IS 'Test column comment time ';
COMMENT ON COLUMN "table"."field_0b" IS 'Test column comment timetz ';
COMMENT ON COLUMN "table"."field_0c" IS 'Test column comment timestamp ';
COMMENT ON COLUMN "table"."field_0d" IS 'Test column comment timestamptz';




drop function if EXISTS "NewProc"(
	IN "parm_a" int2 ,
	"parm_b" smallint ,
	"parm_c" int4 ,
	"parm_d" integer ,
	"parm_e" oid ,
	"parm_g" int8 ,
	"parm_h" bigint ,
	"parm_j" money ,
	"parm_k" numeric(2,2),
	"parm_l" float4 ,
	"parm_m" float8 ,
	"parm_n" real ,
	"parm_q" bpchar ,
	"parm_r" varchar(20) ,
	"parm_s" char (20) ,
	"parm_t" text ,
	"parm_u" name ,
	"parm_v" bytea ,
	"parm_w" bool ,
	"parm_x" boolean ,
	"parm_y" bit ,
	"parm_z" date ,
	"parm_0a" time ,
	"parm_0b" timetz ,
	"parm_0c" timestamp ,
	"parm_0d" timestamptz
);


CREATE OR REPLACE FUNCTION "NewProc"(
	"parm_a" int2 ,
	"parm_b" smallint ,
	"parm_c" int4 ,
	"parm_d" integer ,
	"parm_e" oid ,
	"parm_g" int8 ,
	"parm_h" bigint ,
	"parm_j" money ,
	"parm_k" numeric ,
	"parm_l" float4 ,
	"parm_m" float8 ,
	"parm_n" real ,
	"parm_q" bpchar ,
	"parm_r" varchar ,
	"parm_s" char ,
	"parm_t" text ,
	"parm_u" name ,
	"parm_v" bytea ,
	"parm_w" bool ,
	"parm_x" boolean ,
	"parm_y" bit ,
	"parm_z" date ,
	"parm_0a" time ,
	"parm_0b" timetz ,
	"parm_0c" timestamp ,
	"parm_0d" timestamptz
)
  RETURNS "pg_catalog"."int8" AS $BODY$BEGIN
	--Routine body goes here...

	RETURN 1234;
END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100

;

COMMENT ON FUNCTION "NewProc"(
	IN "parm_a" int2 ,
	"parm_b" smallint ,
	"parm_c" int4 ,
	"parm_d" integer ,
	"parm_e" oid ,
	"parm_g" int8 ,
	"parm_h" bigint ,
	"parm_j" money ,
	"parm_k" numeric(2,2),
	"parm_l" float4 ,
	"parm_m" float8 ,
	"parm_n" real ,
	"parm_q" bpchar ,
	"parm_r" varchar(20) ,
	"parm_s" char (20) ,
	"parm_t" text ,
	"parm_u" name ,
	"parm_v" bytea ,
	"parm_w" bool ,
	"parm_x" boolean ,
	"parm_y" bit ,
	"parm_z" date ,
	"parm_0a" time ,
	"parm_0b" timetz ,
	"parm_0c" timestamp ,
	"parm_0d" timestamptz
) IS 'Test comment' ;
