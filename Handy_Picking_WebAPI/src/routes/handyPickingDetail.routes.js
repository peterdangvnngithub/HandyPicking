import { 
	Router
} from 'express';

import {
	checkApiStatus,
	check_Exists_HandyPicking_Detail,
	getAll_HandyPicking_Detail,
	createNew_Handy_Picking_Detail,
	checkListHandyDetailsExistence
} from '../controller/handyPickingDetail.controller.js';

const router = Router();

// GET
router.get('/checkApiStatus',				checkApiStatus);
router.get('/handyPickingDetail',			getAll_HandyPicking_Detail);
router.get('/handyPickingDetail/:series',	check_Exists_HandyPicking_Detail);

// POST
router.post('/handyPickingDetail',			createNew_Handy_Picking_Detail);
router.post('/checkExistsDetail',			checkListHandyDetailsExistence);

export default router;
