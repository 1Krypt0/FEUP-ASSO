<script lang="ts">
	import { createEventDispatcher } from "svelte";
    import ColorPicker from "svelte-awesome-color-picker";
    export let hex:string;
    const dispatch = createEventDispatcher();
    function sendColor() {
        dispatch("color", {hex})
    }
</script>

<section class="flex-row flex justify-center">
    <ColorPicker bind:hex on:input={sendColor}  isAlpha={false} canChangeMode={false}/>
</section>