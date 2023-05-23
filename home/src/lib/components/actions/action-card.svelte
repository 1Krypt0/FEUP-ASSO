<script lang="ts">
	import { DropletIcon, PercentIcon, PowerIcon, XIcon } from 'svelte-feather-icons';
	import RangeAction from './range-action.svelte';
	import ColorPickerAction from './color-picker-action.svelte';
    import SvelteTooltip from 'svelte-tooltip';

	export let name: string;
	export let roomID: number;
    
    let setIntensity = true;
    let setColour = false;
    function intensityToBeSet() {
        setIntensity = true;
        setColour = false;
    }
    function colourToBeSet() {
        setIntensity = false;
        setColour = true;
    }
</script>

<section class="flex-col flex drop-shadow-lg bg-white text-xl px-6 py-6 h-3/6 w-2/6 rounded-3xl">
    <div class="flex-row flex justify-between">
        <div class="flex justify-normal text-2xl font-bold gap-3 items-center">
            <slot />
            {name}
        </div>
        <div>
            <a href="/room/{roomID}"><XIcon /></a>
        </div>
    </div>
    <div class="flex-row flex justify-center gap-4 py-6">
        <button><PowerIcon/></button>
        <SvelteTooltip tip="set intensity" bottom >
            <button on:click={intensityToBeSet} class={`rounded-full p-1 ${setIntensity ? "bg-accent" : "bg-light"}`}><PercentIcon/></button>
        </SvelteTooltip>
        <SvelteTooltip tip="set colour" bottom >
            <button on:click={colourToBeSet} class={`rounded-full p-1 ${setColour ? "bg-accent" : "bg-light"}`}><DropletIcon/></button>
        </SvelteTooltip>
    </div>
    <div class="h-full flex-col flex justify-center">
        {#if setIntensity}
            <RangeAction value={60} min={0} max={100} step={1} device_type="light"></RangeAction>
        {:else}
            <ColorPickerAction hex="#8c2d19"></ColorPickerAction>
        {/if}
    </div>
</section>