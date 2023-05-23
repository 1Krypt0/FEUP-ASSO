import type { PageServerLoad } from './$types';
import type { Workflow } from '$lib/types/workflow';
import { Condition } from '$lib/types/condition';
import { Operation } from '$lib/types/operation';

export const load = (async ({ params, parent }) => {
	const parentData = await parent();

	// TODO: actually fetch devices and workflows

	let workflows: Workflow[] = [
		{
			id: 1,
			active: true,
			name: 'My first workflow',
			eventNodes: [
				{
					device: 1,
					action: 1,
					conditionNodes: [
						{
							condition: Condition.EQ,
							value: '1',
							operation: Operation.NULL
						}
					],
					operation: Operation.AND
				},
				{
					device: 1,
					action: 2,
					conditionNodes: [
						{
							condition: Condition.GT,
							value: '10',
							operation: Operation.AND
						},
						{
							condition: Condition.LT,
							value: '30',
							operation: Operation.AND
						}
					],
					operation: Operation.NULL
				}
			],
			actionNode: {
				device: 1,
				action: 1,
				value: '0'
			}
		}
	];

	return { workflows };
}) satisfies PageServerLoad;
