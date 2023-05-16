<script lang="ts">
	import type { PageData } from './$types';

	export let data: PageData;
</script>

<div class="flex flex-col w-full">
	<h1 class="font-bold pl-20 pt-12 text-4xl">Add a device</h1>

	<section class="w-full h-full flex items-center justify-center">
		<form method="post" class="w-1/2 h-2/3 flex flex-col items-center gap-6">
			<select>
				<option>No Room</option>
				{#each data.rooms as room}
					<option>{room.name}</option>
				{/each}
			</select>
		</form>
	</section>
</div>
