import { getConnection } from '../database/connection.js';
import sql from 'mssql';

export const check_Exists_HandyPicking_MS = async (req, res) => {
	try {
		const pool = await getConnection();
		const result = await pool.request().input("plNo", req.params.plNo).query('SELECT * FROM HANDY_PICKING_MS WHERE PICKING_LIST_NO = @plNo');
		console.log(result);	
		res.json(result);
	} catch (error) {
		res.status(500);
		res.send(result);
	}
};

export const getAll_HandyPicking_MS = async (req, res) => {
	try {
		const pool = await getConnection();
		const result = await pool.request().query('SELECT * FROM HANDY_PICKING_MS');
		console.log(result);
		res.json(result.recordset);
	} catch (error) {
		res.status(500);
		res.send(error.message);
	}
};

export const createNew_Handy_Picking_MS = async (req, res) => {
	var result = req.body;

	// // Check error before add data
	// for (let i = 0; i <= result.length - 1; i++) {
	// 	if (
	// 		result[i]['PICKING_CODE'] == null ||
	// 		result[i]['ITEM_CODE'] == null ||
	// 		result[i]['SERIES'] == null ||
	// 		result[i]['QUANTITY'] == null ||
	// 		result[i]['CREATE_DATE'] == null ||
	// 		result[i]['CREATE_BY'] == null
	// 	) {
	// 		return res.status(400).json({ msg: 'One of data fields is null. Please fill all fields' });
	// 	}
	// }

	try {
		const pool = await getConnection();

		console.log(result[0]);

		pool.connect().then(() => {
			const table = new sql.Table('HANDY_PICKING_MS');
			table.columns.add('CUSTOMER_CODE',		sql.VarChar,	{ nullable: false });
			table.columns.add('PICKING_LIST_NO',	sql.VarChar,	{ nullable: false, primary: true });
			table.columns.add('DELIVERY_ADDRESS',	sql.NVarChar,	{ nullable: false });
			table.columns.add('SALE_ORDER',			sql.VarChar,	{ nullable: false });
			table.columns.add('ITEM_CODE',			sql.VarChar,	{ nullable: false });
			table.columns.add('LOT_ID',				sql.VarChar,	{ nullable: false });
			table.columns.add('QUANTITY',			sql.Int,		{ nullable: false });
			table.columns.add('EMPLOYEE_CODE',		sql.VarChar,	{ nullable: false });
			table.columns.add('COLUMN6',			sql.VarChar,	{ nullable: true  });
			table.columns.add('CREATE_DATE',		sql.DateTime,	{ nullable: false });
			table.columns.add('CREATE_BY',			sql.VarChar,	{ nullable: false });
			table.columns.add('EDIT_DATE',			sql.DateTime,	{ nullable: true  });
			table.columns.add('EDIT_BY',			sql.VarChar,	{ nullable: true  });
			table.columns.add('COLUMN1',			sql.VarChar,	{ nullable: true  });
			table.columns.add('COLUMN2',			sql.VarChar,	{ nullable: true  });
			table.columns.add('COLUMN3',			sql.VarChar,	{ nullable: true  });
			table.columns.add('COLUMN4',			sql.VarChar,	{ nullable: true  });
			table.columns.add('COLUMN5',			sql.VarChar,	{ nullable: true  });

			for (let i = 0; i <= result.length - 1; i++) {
				table.rows.add(
					result[i]['CUSTOMER_CODE'],
					result[i]['PICKING_LIST_NO'],
					result[i]['DELIVERY_ADDRESS'],
					result[i]['SALE_ORDER'],
					result[i]['ITEM_CODE'],
					result[i]['LOT_ID'],
					result[i]['QUANTITY'],
					result[i]['EMPLOYEE_CODE'],
					result[i]['COLUMN6'],
					new Date(),
					result[i]['CREATE_BY'],
					result[i]['EDIT_DATE'],
					result[i]['EDIT_BY'],
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
					console.log('ERROR post bulk gebruikers: ' + err);
				} else {
					res.send('SUCCESS!');
				}
			});
		});
	} catch (error) {
		console.error(error.message);
		res.status(500);
		res.send(error.message);
	}
};
