<script lang="ts">
	import ActionCard from '$lib/components/actions/action-card.svelte';
	import { SunIcon } from 'svelte-feather-icons';
import type { PageData } from './$types';
	export let data: PageData;
</script>

<div class="flex-col flex items-center justify-center w-full">
	<ActionCard name={data.device.name} roomID={data.device.room}><SunIcon class="stroke-accent" /></ActionCard>
</div>
