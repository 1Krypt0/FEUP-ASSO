import type { PageServerLoad } from './$types';
import type { Workflow } from '$lib/types/workflow';
import { Condition } from '$lib/types/condition';
import { Operation } from '$lib/types/operation';

export const load = (async ({ params, parent }) => {
	const parentData = await parent();

	// TODO: fetch workflows
	let workflows: Workflow[] = [
		{
			id: '1',
			active: true,
			name: 'My first workflow',
			eventNodes: [
				{
					device: '123',
					action: 'bool',
					conditionNodes: [
						{
							condition: Condition.EQ,
							value: '1',
							operation: Operation.AND
						}
					],
					operation: Operation.AND
				},
				{
					device: '123',
					action: 'range',
					conditionNodes: [
						{
							condition: Condition.GT,
							value: '10',
							operation: Operation.NULL
						}
					],
					operation: Operation.NULL
				}
			],
			actionNode: {
				device: '123',
				action: 'bool',
				value: '0'
			}
		}
	];

	return { workflows };
}) satisfies PageServerLoad;
