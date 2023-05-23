import type { PageServerLoad } from './$types';
// import { getDevice } from '$lib/services/devices';

interface Properties {
	min: number | null
	max: number | null
	step: number | null
}

interface Action {
	type: string
	name: string
	updatable: boolean
}

interface DeviceAction {
	id: string,
	name: string
	properties: Properties | null
	status: string
	displayName: string
}

interface Device {
	id: number;
	name: string;
	deviceActions: DeviceAction[]
}

export const load = (async ({ params, parent }) => {
	//const device = await getDevice(params.id);

	// TODO: Change to data fetching when available
	const device = 		
	{
    "id": 3,
    "dataTopic": "DATA-BB:AA",
    "actionTopic": "ACTION-BB:AA",
    "name": "Virtual Iota Colorful 1.0",
    "macAddress": "BB:AA",
    "added": false,
    "status": "CONNECTED",
    "deviceActions": [
        {
            "id": 4,
            "name": "intensity",
            "displayName": "intensity",
            "properties": {
                "min": "0",
                "max": "100",
                "step": "1"
            },
            "status": "150",
            "action": {
                "type": "number",
                "name": "range",
                "updatable": true,
            }
        },
        {
            "id": 3,
            "name": "toggle",
            "displayName": "toggle",
            "properties": {},
            "status": "7ec0ee",
            "action": {
                "id": 1,
                "type": "bool",
                "name": "toggle",
                "updatable": true,
            }
        }
    ]
}
		
	return { device };
}) satisfies PageServerLoad;
