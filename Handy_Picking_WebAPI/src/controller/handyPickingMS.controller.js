import { getConnection } from '../database/connection.js';
import sql from 'mssql';

export const check_Exists_HandyPicking_MS = async (req, res) => {
	try {
		const pool = await getConnection();
		const result = await pool
		.request()
		.input("plNo", req.params.plNo)
		.query('SELECT * FROM HANDY_PICKING_MS WHERE PICKING_LIST_NO = @plNo');

		console.log(result.recordset);	
		res.json(result.recordset);
	} catch (error) {
		console.error(error);
		res.status(500).send("Internal Server Error");
	}
};

export const getAll_HandyPicking_MS = async (req, res) => {
	try {
		const pool = await getConnection();
		const result = await pool.request().query('SELECT * FROM HANDY_PICKING_MS WHERE STATUS = 0 ORDER BY CREATE_DATE DESC');
		console.log(result);
		res.json(result.recordset);
	} catch (error) {
		res.status(500).send(error.message);
	
	}
};

export const createNew_Handy_Picking_MS = async (req, res) => {
	var result = req.body;

	try {
		const pool = await getConnection();

		console.log(result[0]);

		pool.connect().then(() => {
			const table = new sql.Table('HANDY_PICKING_MS');
			table.columns.add('CUSTOMER_CODE',			sql.VarChar(30),	{ nullable: false });
			table.columns.add('PICKING_LIST_NO',		sql.VarChar(150),	{ nullable: false, primary: true });
			table.columns.add('DELIVERY_ADDRESS_CODE',	sql.VarChar(30),	{ nullable: true });
			table.columns.add('DELIVERY_ADDRESS_NAME',	sql.NVarChar(300),	{ nullable: false });
			table.columns.add('EMPLOYEE_CODE',			sql.VarChar(10),	{ nullable: false });
			table.columns.add('CREATE_DATE',			sql.DateTime,		{ nullable: false });
			table.columns.add('CREATE_BY',				sql.VarChar(50),	{ nullable: false });
			table.columns.add('EDIT_DATE',				sql.DateTime,		{ nullable: true  });
			table.columns.add('EDIT_BY',				sql.VarChar(50),	{ nullable: true  });
			table.columns.add('STATUS',					sql.Int,			{ nullable: true  });
			table.columns.add('COLUMN1',				sql.VarChar(30),	{ nullable: true  });
			table.columns.add('COLUMN2',				sql.VarChar(30),	{ nullable: true  });
			table.columns.add('COLUMN3',				sql.VarChar(30),	{ nullable: true  });
			table.columns.add('COLUMN4',				sql.VarChar(30),	{ nullable: true  });
			table.columns.add('COLUMN5',				sql.VarChar(30),	{ nullable: true  });

			for (let i = 0; i <= result.length - 1; i++) {
				table.rows.add(
					result[i]['CUSTOMER_CODE'],
					result[i]['PICKING_LIST_NO'],
					result[i]['DELIVERY_ADDRESS_CODE'],
					result[i]['DELIVERY_ADDRESS_NAME'],
					result[i]['EMPLOYEE_CODE'],
					new Date(result[i]['CREATE_DATE']),
					result[i]['CREATE_BY'],
					result[i]['EDIT_DATE'],
					result[i]['EDIT_BY'],
					result[i]['STATUS'],
					result[i]['COLUMN1'],
					result[i]['COLUMN2'],
					result[i]['COLUMN3'],
					result[i]['COLUMN4'],
					result[i]['COLUMN5']
				);
			}

			const request = new sql.Request();
			const result_Insert = request.bulk(table, (err, result) => {
				// Error checks
				if (err) {
					console.log('Post bulk error: ' + err);
					res.status(500).send({ message: 'Bulk insert operation failed.' });
				} else {
					console.log('Post bulk success!');
					res.status(200).send({ message: 'Bulk insert operation successful.' });
				}
			});
		});
	} catch (error) {
		console.error(error.message);
		res.status(500);
		res.send(error.message);
	}
};
